/*******************************************************************************
 * Copyright (c) 2017 Institute for Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.cthing.cmakeed.parser.ast;

/**
 * A CMake command invocation like <code>add_executable(tgt sourcefile)</code>
 * 
 * @since 1.0.0
 * @noimplement
 * @noextend
 */
public interface CMakeASTNodeCommandInvocation extends CMakeASTNodeNamedElement {

	/**
	 * Retrieve the arguments of the CMake command invocation
	 * 
	 * @return A (possibly empty) {@link Iterable} containing the invocation's
	 *         arguments.
	 * @since 1.0.0
	 */
	Iterable<CMakeASTNode> getArguments();

}
