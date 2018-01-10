package com.cthing.cmakeed.parser.tests.ast;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeCommandInvocation;
import com.cthing.cmakeed.parser.tests.CMakeTestCode;

public class CMakeASTFileTest extends CMakeASTTest {

	@Test
	@CMakeTestCode("")
	public void emptySourceFileProducesFileWithoutChildrenOrParent() throws Exception {
		CMakeASTNode ast = getAST();

		assertTrue(ast.getChildren().isEmpty());
		assertThat(ast.getParent(), equalTo(Optional.empty()));
	}

	@Test
	@CMakeTestCode("cmd()\n")
	public void sourceFileContainingOneCommandInvocationWithoutArgumentsProducesFileWithOneChild() throws Exception {
		CMakeASTNode ast = getAST();

		assertThat(ast.getChildren().size(), equalTo(1));
		assertTrue(ast.getChildren().toArray()[0] instanceof CMakeASTNodeCommandInvocation);
		assertThat(ast.getChildren().toArray(new CMakeASTNode[1])[0].getParent(), equalTo(Optional.of(ast)));
	}

	@Test
	@CMakeTestCode("cmd(SOME UNQUOTED ARGUMENTS)\n")
	public void sourceFileContainingOneCommandInvocationWithArgumentsProducesFileWithOneChild() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertThat(ast.getChildren().size(), equalTo(1));
		assertTrue(ast.getChildren().toArray()[0] instanceof CMakeASTNodeCommandInvocation);
	}
	
	@Ignore
	@Test
	@CMakeTestCode("#A comment without a whitespace after the pound sign\n")
	public void sourceFileContainigOneCommentProducesFileWithOneChild() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertThat(ast.getChildren().size(), equalTo(1));
		assertTrue(ast.getChildren().toArray()[0] instanceof CMakeASTNodeCommandInvocation);
	}
}
