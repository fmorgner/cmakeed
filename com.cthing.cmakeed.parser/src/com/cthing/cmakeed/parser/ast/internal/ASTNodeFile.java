package com.cthing.cmakeed.parser.ast.internal;

import org.antlr.v4.runtime.ParserRuleContext;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeFile;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;

/**
 * A CMake file that is either a directory (CMakeLists.txt), a script
 * (\<scriptname\>.cmake), or a module (\<modulename\>.cmake).
 * 
 * @noextend
 * @since 1.0.0
 */
public class ASTNodeFile extends ASTNode implements CMakeASTNodeFile {

	protected ASTNodeFile(ParserRuleContext context) {
		super(context);
	}

	@Override
	protected Decision doAccept(CMakeASTVisitor visitor) {
		return visitor.visit(this);
	}
	
}