package com.cthing.cmakeed.parser.ast;

import java.util.Optional;

import org.eclipse.core.resources.IFile;

public interface CMakeASTProvider {

	/**
	 * Get the AST associated with the given source text
	 * 
	 * @param sourceText
	 *            A string containing CMake source text
	 * @return An AST node representing the root of the AST, or an empty
	 *         {@link Optional} if the source text could not be read.
	 */
	public Optional<CMakeASTNode> getAST(String sourceText);

	/**
	 * Get the AST associated with the given source file
	 * 
	 * @param sourceFile
	 *            A file containing CMake source text
	 * @return An AST node representing the root of the AST, or an empty
	 *         {@link Optional} if the source file could not be read.
	 */
	public Optional<CMakeASTNode> getAST(IFile sourceFile);

}
