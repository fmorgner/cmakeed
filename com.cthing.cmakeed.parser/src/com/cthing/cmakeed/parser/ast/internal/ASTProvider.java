package com.cthing.cmakeed.parser.ast.internal;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTProvider;
import com.cthing.cmakeed.parser.llparser.CMakeLexer;
import com.cthing.cmakeed.parser.llparser.CMakeParser;

public class ASTProvider implements CMakeASTProvider {

	private final ASTBuilder fBuilder = new ASTBuilder();
	
	@Override
	public CMakeASTNode getAST(String sourceText) {
		ANTLRInputStream input = new ANTLRInputStream(sourceText);
		CMakeLexer lexer = new CMakeLexer(input);
		CommonTokenStream stream = new CommonTokenStream(lexer);
		CMakeParser parser = new CMakeParser(stream);
		return parser.file().accept(fBuilder);
	}

}
