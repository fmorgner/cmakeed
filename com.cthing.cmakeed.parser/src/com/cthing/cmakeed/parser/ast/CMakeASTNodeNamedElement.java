/*******************************************************************************
 * Copyright (c) 2017 Institute for Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.cthing.cmakeed.parser.ast;

/**
 * The interface supported by named nodes, like command invocations and variables
 * 
 * @since 1.0.0
 * @noimplement
 * @noextend
 */
public interface CMakeASTNodeNamedElement extends CMakeASTNode {

	/**
	 * Retrieve the name of the node
	 * 
	 * @return The (possibly empty) name of the node
	 */
	String getName();
	
}
