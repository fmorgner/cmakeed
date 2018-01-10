package com.cthing.cmakeed.parser.ast;

public interface CMakeASTProvider {

	public CMakeASTNode getAST(String sourceText);
	
}
