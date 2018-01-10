package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeArgument;

public class Argument extends AbstractNode implements CMakeASTNodeArgument {

	private final String fValue;
	
	Argument(String value) {
		fValue = value;
	}
	
	@Override
	public String getValue() {
		return fValue;
	}
	
	@Override
	public String toString() {
		return fValue;
	}
}
