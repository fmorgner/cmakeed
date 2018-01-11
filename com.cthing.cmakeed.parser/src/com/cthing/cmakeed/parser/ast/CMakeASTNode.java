/*******************************************************************************
 * Copyright (c) 2017 Institute for Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.cthing.cmakeed.parser.ast;

import java.util.Collection;
import java.util.Optional;

/**
 * The generic interface supported by all AST nodes produced by the parser.
 * 
 * @since 1.0.0
 * @noimplement
 * @noextend
 */
public interface CMakeASTNode {

	/**
	 * Retrieve the child nodes of this node
	 * 
	 * @return A {@link Collection} containing 0 or more {@link CMakeASTNodes}
	 * @since 1.0.0
	 */
	Collection<CMakeASTNode> getChildren();

	/**
	 * Retrieve the parent node of this node
	 * 
	 * @return An {@link Optional} containing the parent of this node iff. this node
	 *         has a parent, an empty {@link Optional} otherwise.
	 * @since 1.0.0
	 */
	Optional<CMakeASTNode> getParent();

	/**
	 * Accept the given visitor
	 * 
	 * @return <code>true</code> iff. traversal should continue, <code>false</code>
	 *         otherwise.
	 * @since 1.0.0
	 */
	boolean accept(CMakeASTVisitor visitor);
	
	int getLineNumber();
	
	int getStartIndex();
	
	int getStopIndex();
}
