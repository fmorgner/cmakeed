package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.cthing.cmakeed.parser.llparser.CMakeBaseVisitor;
import com.cthing.cmakeed.parser.llparser.CMakeParser.ArgumentsContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.CommandInvocationContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.FileContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.UnquotedArgumentContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.Unquoted_elementContext;

public class ASTBuilder extends CMakeBaseVisitor<ASTNode> {

	private static class ArgumentCollector extends CMakeBaseVisitor<Void> {
		
		private final List<ASTNodeArgument> arguments = new ArrayList<>();
		
		@Override
		public Void visitUnquotedArgument(UnquotedArgumentContext ctx) {
			ctx.unquoted_element().stream()
				.map(Unquoted_elementContext::getText)
				.map(ASTNodeArgument::new)
				.forEach(arguments::add);
			return null;
		}
		
		public static Stream<ASTNodeArgument> collect(ArgumentsContext ctx) {
			ArgumentCollector collector = new ArgumentCollector();
			ctx.accept(collector);
			return collector.arguments.stream();
		}
		
	}
	
	@Override
	public ASTNode visitFile(FileContext ctx) {
		ASTNodeFile file = new ASTNodeFile();

		ctx.file_element().stream()
			.map(e -> e.accept(this))
			.map(Optional::ofNullable)
			.forEach(o -> o.map(e -> e.setParent(file)));

		return file;
	}

	@Override
	public ASTNode visitCommandInvocation(CommandInvocationContext ctx) {
		final ASTNodeCommandInvocation invocation = new ASTNodeCommandInvocation(ctx.name.id.getText());
		Stream<ASTNodeArgument> collect = ArgumentCollector.collect(ctx.arguments());
		collect.forEach(a -> a.setParent(invocation));
		return invocation;
	}
	
	@Override
	protected ASTNode aggregateResult(ASTNode aggregate, ASTNode nextResult) {
		if(aggregate != null) {
			if(nextResult != null) {
				nextResult.setParent(aggregate);
			}
		
			return aggregate;
		}
		
		return nextResult;
	}
	
}
