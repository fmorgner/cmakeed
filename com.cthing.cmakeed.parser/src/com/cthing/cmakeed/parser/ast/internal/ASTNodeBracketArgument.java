package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.llparser.CMakeParser.BracketedContentContext;

public class ASTNodeBracketArgument extends ASTNodeArgument {

	protected ASTNodeBracketArgument(BracketedContentContext context) {
		super(context);
		fValue = context.getText();
	}

}
