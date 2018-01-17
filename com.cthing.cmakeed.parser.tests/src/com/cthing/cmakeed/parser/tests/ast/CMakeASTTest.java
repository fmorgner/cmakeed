package com.cthing.cmakeed.parser.tests.ast;

import java.util.Optional;

import org.junit.runner.RunWith;

import com.cthing.cmakeed.parser.CMakeParserPlugin;
import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTProvider;

@RunWith(CMakeASTTestRunner.class)
public abstract class CMakeASTTest {

	private String fSourceText;
	
	protected Optional<CMakeASTNode> getAST() {
		CMakeASTProvider provider = CMakeParserPlugin.getService(CMakeASTProvider.class);
		return provider.getAST(fSourceText);
	}
	
}
