package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.cthing.cmakeed.parser.CMakeParserError;
import com.cthing.cmakeed.parser.llparser.CMakeBaseVisitor;
import com.cthing.cmakeed.parser.llparser.CMakeParser.ArgumentsContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.CommandInvocationContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.FileContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.UnquotedArgumentContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.UnquotedElementContext;
import com.cthing.cmakeed.parser.llparser.CMakeParser.VariableReferenceContext;

public class ASTBuilder extends CMakeBaseVisitor<ASTNode> {

	private Deque<ASTNode> fParentStack = new ArrayDeque<>();
	
	private static final List<String> BLOCK_START_DESIGNATORS = Arrays.asList(
		"function",
		"macro",
		"foreach",
		"while",
		"if"
	);
	
	private static final List<String> BLOCK_CHANGE_DESIGNATORS = Arrays.asList(
		"elseif",
		"else"
	);

	private static final List<String> BLOCK_END_DESIGNATORS = Arrays.asList(
		"endfunction",
		"endmacro",
		"endforeach",
		"endwhile",
		"endif"
	);
	
	private static final String UNEXPECTED_BLOCK_END = "Block end designator '%s' encountered without active block";
	
	private static final String UNMATCHED_BLOCK_END = "Unmatched block end designator '%s', expected 'end%s'"; 
	
	private static final String UNEXPECTED_BLOCK_CHANGE = "Block change designator '%s' encountered without active block";
	
	private static final String UNMATCHED_BLOCK_CHANGE = "Block change designator '%s' encountered outside of if block"; 
	
	private static class ArgumentCollector extends CMakeBaseVisitor<Void> {
		
		private final List<ASTNodeArgument> arguments = new ArrayList<>();
		
		@Override
		public Void visitUnquotedArgument(UnquotedArgumentContext ctx) {
			ctx.getRuleContexts(UnquotedElementContext.class).stream()
				.map(ASTNodeUnquotedArgument::new)
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
		ASTNodeFile file = new ASTNodeFile(ctx);
		fParentStack.push(file);

		ctx.file_element().stream()
			.map(e -> e.accept(this))
			.map(Optional::ofNullable)
			.forEach(o -> o.map(e -> e.setParent(fParentStack.peek())));

		fParentStack.pop();
		return file;
	}

	@Override
	public ASTNode visitCommandInvocation(CommandInvocationContext ctx) {
		final ASTNodeCommandInvocation invocation = new ASTNodeCommandInvocation(ctx);
		handleArguments(ctx, invocation);
		handleCommandBlock(invocation);
		return invocation;
	}
	
	@Override
	public ASTNode visitVariableReference(VariableReferenceContext ctx) {
		ASTNodeVariableReference reference = new ASTNodeVariableReference(ctx);
		return reference;
	}

	private void handleArguments(CommandInvocationContext ctx, final ASTNodeCommandInvocation invocation) {
		fParentStack.push(invocation);
		Stream<ASTNodeArgument> collect = ArgumentCollector.collect(ctx.arguments());
		collect.forEach(a -> a.setParent(fParentStack.peek()));
		fParentStack.pop();
	}
	
	private void handleCommandBlock(ASTNodeCommandInvocation invocation) {
		final String normalizedName = invocation.getName().toLowerCase();
		
		if(BLOCK_START_DESIGNATORS.contains(normalizedName)) {
			handleBlockStart(invocation);
		} else if (BLOCK_CHANGE_DESIGNATORS.contains(normalizedName)) {
			handleBlockChange(invocation);
		} else if (BLOCK_END_DESIGNATORS.contains(normalizedName)) {
			handleBlockEnd(invocation);
		}
	}

	private void handleBlockEnd(ASTNodeCommandInvocation invocation) {
		final String normalizedName = invocation.getName().toLowerCase();
		
		ASTNode currentBlock = fParentStack.pop();
		if(!(currentBlock instanceof ASTNodeCommandInvocation)) {
			fParentStack.push(currentBlock);
			throw new CMakeParserError(String.format(UNEXPECTED_BLOCK_END, normalizedName));
		}
		
		String activeBlockName = ((ASTNodeCommandInvocation) currentBlock).getName().toLowerCase();
		if(BLOCK_CHANGE_DESIGNATORS.contains(activeBlockName)) {
			currentBlock = fParentStack.pop();
			activeBlockName = ((ASTNodeCommandInvocation) currentBlock).getName().toLowerCase();
		}
		
		if(!("end" + activeBlockName).equals(normalizedName)) {
			throw new CMakeParserError(String.format(UNMATCHED_BLOCK_END, normalizedName, activeBlockName));
		}
	}

	private void handleBlockChange(ASTNodeCommandInvocation invocation) {
		final String normalizedName = invocation.getName().toLowerCase();
		ASTNode currentBlock = fParentStack.peek();
		if(!(currentBlock instanceof ASTNodeCommandInvocation)) {
			throw new CMakeParserError(String.format(UNEXPECTED_BLOCK_CHANGE, normalizedName));
		}
		
		String blockName = ((ASTNodeCommandInvocation) currentBlock).getName().toLowerCase();
		if(!blockName.equals("if")) {
			throw new CMakeParserError(String.format(UNMATCHED_BLOCK_CHANGE, normalizedName));
		}
		
		invocation.setParent(currentBlock);
		fParentStack.push(invocation);
	}

	private void handleBlockStart(ASTNodeCommandInvocation invocation) {
		invocation.setParent(fParentStack.peek());
		fParentStack.push(invocation);
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
