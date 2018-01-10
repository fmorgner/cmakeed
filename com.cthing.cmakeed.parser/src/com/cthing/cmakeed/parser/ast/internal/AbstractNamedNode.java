package com.cthing.cmakeed.parser.ast.internal;

import java.util.Objects;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeNamedElement;

class AbstractNamedNode extends AbstractNode implements CMakeASTNodeNamedElement {

	protected final String fName;
	
	protected AbstractNamedNode(String name) {
		fName = Objects.requireNonNull(name);
	}
	
	@Override
	public String getName() {
		return fName;
	}

}
