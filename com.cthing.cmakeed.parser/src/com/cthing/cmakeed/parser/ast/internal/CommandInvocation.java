package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.List;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeCommandInvocation;
import com.cthing.cmakeed.parser.ast.CMakeASTNode;

public class CommandInvocation extends AbstractNamedNode implements CMakeASTNodeCommandInvocation {

	private final List<CMakeASTNode> fArguments = new ArrayList<>();
	
	protected CommandInvocation(String name) {
		super(name);
	}

	@Override
	public Iterable<CMakeASTNode> getArguments() {
		return fArguments;
	}

	@Override
	protected void add(AbstractNode abstractNode) {
		super.add(abstractNode);
		if(abstractNode instanceof Argument) {
			fArguments.add(abstractNode);
		}
	}
	
}
