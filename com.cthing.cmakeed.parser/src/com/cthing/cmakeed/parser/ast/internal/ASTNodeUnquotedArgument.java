package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeUnquotedArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;
import com.cthing.cmakeed.parser.llparser.CMakeParser.UnquotedElementContext;

public class ASTNodeUnquotedArgument extends ASTNodeEvaluatedArgument implements CMakeASTNodeUnquotedArgument {

	protected ASTNodeUnquotedArgument(UnquotedElementContext context) {
		super(context);
	}

	@Override
	protected Decision doAccept(CMakeASTVisitor visitor) {
		return visitor.visit(this);
	} 
	
}
