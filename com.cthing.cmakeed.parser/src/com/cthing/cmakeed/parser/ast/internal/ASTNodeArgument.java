package com.cthing.cmakeed.parser.ast.internal;

import org.antlr.v4.runtime.ParserRuleContext;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeArgument;

public abstract class ASTNodeArgument extends ASTNode implements CMakeASTNodeArgument {

	protected ASTNodeArgument(ParserRuleContext context) {
		super(context);
	}

	protected String fValue = null;
	
	@Override
	public String getValue() {
		return fValue;
	}
	
	@Override
	public String toString() {
		return fValue;
	}
}
