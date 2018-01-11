package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.llparser.CMakeParser.Unquoted_elementContext;

public class ASTNodeUnquotedArgument extends ASTNodeArgument {

	protected ASTNodeUnquotedArgument(Unquoted_elementContext context) {
		super(context);
		
		String value = context.getText();
		
		value = value.replaceAll("\\\\r", "\r");
		value = value.replaceAll("\\\\n", "\n");
		value = value.replaceAll("\\\\t", "\t");
		fValue = value.replaceAll("\\\\", "");
	}

}
