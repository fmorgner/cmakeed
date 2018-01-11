package com.cthing.cmakeed.parser.ast.internal;

import org.antlr.v4.runtime.ParserRuleContext;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeNamedElement;

abstract class ASTNodeNamedElement extends ASTNode implements CMakeASTNodeNamedElement {

	protected String fName;
	
	protected ASTNodeNamedElement(ParserRuleContext context) {
		super(context);
	}
	
	@Override
	public String getName() {
		return fName;
	}

	@Override
	public String toString() {
		return getName();
	}
}
