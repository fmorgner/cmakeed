/*******************************************************************************
 * Copyright (c) 2017 Institute for Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.cthing.cmakeed.parser.ast;

/**
 * The interface of an AST visitor.
 * 
 * @since 1.0.0
 */
public interface CMakeASTVisitor {

	public static enum Decision {
		/**
		 * Continue AST traversal
		 */
		CONTINUE,

		/**
		 * Abort the whole AST traversal
		 */
		ABORT,

		/**
		 * Skip the children of the current node during traversal
		 */
		SKIP,
	}

	/**
	 * Callback that gets called when a file (CMakeLists.txt, CMake script, CMake
	 * module) is encountered during AST traversal.
	 * 
	 * @param file
	 *            The {@link CMakeASTNode} representing the file being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visit(CMakeASTNodeFile file) {
		return Decision.CONTINUE;
	}

	/**
	 * Callback that gets called when a "Bracket Argument" is encountered during AST
	 * traversal.
	 * 
	 * @param file
	 *            The {@link CMakeASTNode} representing the file being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visit(CMakeASTNodeBracketArgument argument) {
		return Decision.CONTINUE;
	}

	/**
	 * Callback that gets called when a "Quoted Argument" is encountered during AST
	 * traversal.
	 * 
	 * @param file
	 *            The {@link CMakeASTNode} representing the file being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visit(CMakeASTNodeQuotedArgument argument) {
		return Decision.CONTINUE;
	}

	/**
	 * Callback that gets called when a "Unquoted Argument" is encountered during
	 * AST traversal.
	 * 
	 * @param file
	 *            The {@link CMakeASTNode} representing the file being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visit(CMakeASTNodeUnquotedArgument argument) {
		return Decision.CONTINUE;
	}

	/**
	 * Callback that gets called when a "Command Invocation" is encountered during
	 * AST traversal.
	 * 
	 * @param file
	 *            The {@link CMakeASTNode} representing the file being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visit(CMakeASTNodeCommandInvocation argument) {
		return Decision.CONTINUE;
	}

	/**
	 * Callback that gets called when a "Variable Reference" is encountered during
	 * AST traversal.
	 * 
	 * @param file
	 *            The {@link CMakeASTNode} representing the file being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visit(CMakeASTNodeVariableReference variable) {
		return Decision.CONTINUE;
	}

	/**
	 * Callback that gets called when a command invocation is encountered during AST
	 * traversal.
	 * 
	 * @param invocation
	 *            The {@link CMakeASTNodeCommandInvocation} representing the command
	 *            invocation being visited.
	 * @return A {@link Decision} describing whether to continue traversal, skip the
	 *         node's children, or abort the whole traversal.
	 */
	default Decision visitCommandInvocation(CMakeASTNodeCommandInvocation invocation) {
		return Decision.CONTINUE;
	}

}
