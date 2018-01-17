package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeQuotedArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;
import com.cthing.cmakeed.parser.llparser.CMakeParser.QuotedElementContext;

public class ASTNodeQuotedArgument extends ASTNodeEvaluatedArgument implements CMakeASTNodeQuotedArgument {

	protected ASTNodeQuotedArgument(QuotedElementContext context) {
		super(context);
	}
	
	@Override
	protected Decision doAccept(CMakeASTVisitor visitor) {
		return visitor.visit(this);
	}

}
