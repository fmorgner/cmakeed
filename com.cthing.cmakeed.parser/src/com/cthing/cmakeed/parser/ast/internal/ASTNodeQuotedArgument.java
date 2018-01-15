package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.llparser.CMakeParser.QuotedElementContext;

public class ASTNodeQuotedArgument extends ASTNodeEvaluatedArgument {

	protected ASTNodeQuotedArgument(QuotedElementContext context) {
		super(context);
	}

	
}
