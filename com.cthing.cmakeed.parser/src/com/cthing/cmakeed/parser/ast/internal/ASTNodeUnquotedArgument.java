package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.llparser.CMakeParser.UnquotedElementContext;

public class ASTNodeUnquotedArgument extends ASTNodeEvaluatedArgument {

	protected ASTNodeUnquotedArgument(UnquotedElementContext context) {
		super(context);
	}

}
