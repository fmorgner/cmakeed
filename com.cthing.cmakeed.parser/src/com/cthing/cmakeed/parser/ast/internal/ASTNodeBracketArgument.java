package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeBracketArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;
import com.cthing.cmakeed.parser.llparser.CMakeParser.BracketedContentContext;

public class ASTNodeBracketArgument extends ASTNodeArgument implements CMakeASTNodeBracketArgument {

	protected ASTNodeBracketArgument(BracketedContentContext context) {
		super(context);
		fValue = context.getText();
	}

	@Override
	protected Decision doAccept(CMakeASTVisitor visitor) {
		return visitor.visit(this);
	}
	
}
