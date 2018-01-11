package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.List;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeCommandInvocation;
import com.cthing.cmakeed.parser.ast.CMakeASTNode;

public class ASTNodeCommandInvocation extends ASTNodeNamedElement implements CMakeASTNodeCommandInvocation {

	private final List<CMakeASTNode> fArguments = new ArrayList<>();
	
	protected ASTNodeCommandInvocation(String name) {
		super(name);
	}

	@Override
	public Iterable<CMakeASTNode> getArguments() {
		return fArguments;
	}

	@Override
	protected void add(ASTNode abstractNode) {
		super.add(abstractNode);
		if(abstractNode instanceof ASTNodeArgument) {
			fArguments.add(abstractNode);
		}
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
