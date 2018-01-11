package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeVariableReference;
import com.cthing.cmakeed.parser.llparser.CMakeParser.VariableReferenceContext;

public class ASTNodeVariableReference extends ASTNodeNamedElement implements CMakeASTNodeVariableReference {

	protected ASTNodeVariableReference(VariableReferenceContext context) {
		super(context);
	}

}
