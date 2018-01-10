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

public class ASTBuilder extends CMakeBaseVisitor<AbstractNode> {

	private static class ArgumentCollector extends CMakeBaseVisitor<Void> {
		
		private final List<Argument> arguments = new ArrayList<>();
		
		@Override
		public Void visitUnquotedArgument(UnquotedArgumentContext ctx) {
			ctx.unquoted_element().stream()
				.map(Unquoted_elementContext::getText)
				.map(Argument::new)
				.forEach(arguments::add);
			return null;
		}
		
		public static Stream<Argument> collect(ArgumentsContext ctx) {
			ArgumentCollector collector = new ArgumentCollector();
			ctx.accept(collector);
			return collector.arguments.stream();
		}
		
	}
	
	@Override
	public AbstractNode visitFile(FileContext ctx) {
		File file = new File();

		ctx.file_element().stream()
			.map(e -> e.accept(this))
			.map(Optional::ofNullable)
			.forEach(o -> o.map(e -> e.setParent(file)));

		return file;
	}

	@Override
	public AbstractNode visitCommandInvocation(CommandInvocationContext ctx) {
		final CommandInvocation invocation = new CommandInvocation(ctx.name.id.getText());
		Stream<Argument> collect = ArgumentCollector.collect(ctx.arguments());
		collect.forEach(a -> a.setParent(invocation));
		return invocation;
	}
	
	@Override
	protected AbstractNode aggregateResult(AbstractNode aggregate, AbstractNode nextResult) {
		if(aggregate != null) {
			if(nextResult != null) {
				nextResult.setParent(aggregate);
			}
		
			return aggregate;
		}
		
		return nextResult;
	}
	
}
