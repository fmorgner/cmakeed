package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.List;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeCommandInvocation;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;
import com.cthing.cmakeed.parser.llparser.CMakeParser.CommandInvocationContext;

public class ASTNodeCommandInvocation extends ASTNodeNamedElement implements CMakeASTNodeCommandInvocation {

	private final List<CMakeASTNodeArgument> fArguments = new ArrayList<>();
	
	protected ASTNodeCommandInvocation(CommandInvocationContext context) {
		super(context);
		fName = context.name.getText();
	}

	@Override
	public Iterable<CMakeASTNodeArgument> getArguments() {
		return fArguments;
	}

	@Override
	protected void add(ASTNode abstractNode) {
		super.add(abstractNode);
		if(abstractNode instanceof ASTNodeArgument) {
			fArguments.add((CMakeASTNodeArgument) abstractNode);
		}
	}

	@Override
	protected Decision doAccept(CMakeASTVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getName() + "(");
		boolean first = true;
		for(CMakeASTNode argument : fArguments) {
			if(!first) {
				builder.append(' ');
			}
			builder.append(argument);
			first = false;
		}
		builder.append(")");
		return builder.toString();
	}
}
