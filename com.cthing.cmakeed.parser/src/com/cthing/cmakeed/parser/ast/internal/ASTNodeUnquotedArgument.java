package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.llparser.CMakeParser.Unquoted_elementContext;

public class ASTNodeUnquotedArgument extends ASTNodeArgument {

	protected ASTNodeUnquotedArgument(Unquoted_elementContext context) {
		super(context);
		fValue = context.getText();
	}

}
