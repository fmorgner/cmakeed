package com.cthing.cmakeed.parser.ast.internal;

import java.util.Objects;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeNamedElement;

class ASTNodeNamedElement extends ASTNode implements CMakeASTNodeNamedElement {

	protected final String fName;
	
	protected ASTNodeNamedElement(String name) {
		fName = Objects.requireNonNull(name);
	}
	
	@Override
	public String getName() {
		return fName;
	}

}
