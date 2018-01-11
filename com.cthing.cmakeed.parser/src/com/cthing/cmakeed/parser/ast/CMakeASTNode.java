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
	 * Retrieve the child at the specified (0-based) index
	 * 
	 * @note Indices run from left to right in the tree
	 * 
	 * @param index
	 *            The index of the child
	 * @return An {@link Optional} containing the child at the specified index iff.
	 *         it exists, an empty {@link Optional} otherwise.
	 * @since 1.0.0
	 */
	Optional<CMakeASTNode> getChild(int index);

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

	/**
	 * Retrieve the (1-based) line number where the first token of this node starts 
	 * 
	 * @since 1.0.0
	 */
	int getLineNumber();

	/**
	 * Retrieve the (0-based) character index where the first token of this node starts
	 * 
	 * @since 1.0.0
	 */
	int getStartIndex();

	/**
	 * Retrieve the (0-based) character index where the last token of this node ends
	 * 
	 * @since 1.0.0
	 */
	int getEndIndex();
}
