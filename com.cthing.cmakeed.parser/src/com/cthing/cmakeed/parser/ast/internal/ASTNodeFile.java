package com.cthing.cmakeed.parser.ast.internal;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * A CMake file that is either a directory (CMakeLists.txt), a script
 * (\<scriptname\>.cmake), or a module (\<modulename\>.cmake).
 * 
 * @noextend
 * @since 1.0.0
 */
public class ASTNodeFile extends ASTNode {

	protected ASTNodeFile(ParserRuleContext context) {
		super(context);
	}

}