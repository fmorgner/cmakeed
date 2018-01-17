package com.cthing.cmakeed.parser.ast;

import org.eclipse.core.resources.IFile;

public interface CMakeASTProvider {

	/**
	 * Get the AST associated with the given source text
	 * 
	 * @param sourceText A string containing CMake source text
	 * @return An AST node representing the root of the AST.
	 */
	public CMakeASTNode getAST(String sourceText);

	/**
	 * Get the AST associated with the given source file
	 * 
	 * @param sourceFile A file containing CMake source text
	 * @return An AST node representing the root of the AST.
	 */
	public CMakeASTNode getAST(IFile sourceFile);
	
}
