package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeArgument;

public class ASTNodeArgument extends ASTNode implements CMakeASTNodeArgument {

	private final String fValue;
	
	ASTNodeArgument(String value) {
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
