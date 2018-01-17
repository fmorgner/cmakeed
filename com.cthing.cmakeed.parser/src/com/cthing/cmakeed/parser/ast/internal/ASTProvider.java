package com.cthing.cmakeed.parser.ast.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import com.cthing.cmakeed.parser.CMakeParserPlugin;
import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTProvider;
import com.cthing.cmakeed.parser.llparser.CMakeLexer;
import com.cthing.cmakeed.parser.llparser.CMakeParser;

public class ASTProvider implements CMakeASTProvider {

	private final ASTBuilder fBuilder = new ASTBuilder();
	
	private Optional<CMakeASTNode> getAST(InputStream sourceStream) {
		try {
			ANTLRInputStream input = new ANTLRInputStream(sourceStream);
			CMakeLexer lexer = new CMakeLexer(input);
			CommonTokenStream stream = new CommonTokenStream(lexer);
			CMakeParser parser = new CMakeParser(stream);
			return Optional.of(parser.file().accept(fBuilder));
		} catch (IOException e) {
			CMakeParserPlugin.logError(e, "Error while reading input stream");
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<CMakeASTNode> getAST(String sourceText) {
		return getAST(new ByteArrayInputStream(sourceText.getBytes()));
	}

	@Override
	public Optional<CMakeASTNode> getAST(IFile sourceFile) {
		try {
			return getAST(sourceFile.getContents());
		} catch (CoreException e) {
			CMakeParserPlugin.logError(e, "Failed to read source file");
		}
		
		return Optional.empty();
	}

}
