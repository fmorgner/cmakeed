package com.cthing.cmakeed.parser.tests.ast;

import static com.cthing.cmakeed.parser.tests.support.ReturnValue.returnValue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
	@CMakeTestCode("cmd()")
	public void sourceFileContainingOneCommandInvocationWithoutArgumentsProducesFileWithOneChild() throws Exception {
		CMakeASTNode ast = getAST();

		assertThat(ast.getChildren().size(), equalTo(1));
		assertThat(ast.getChildren(), everyItem(instanceOf(CMakeASTNodeCommandInvocation.class)));
		assertThat(ast.getChildren(), everyItem(returnValue(CMakeASTNode::getParent, equalTo(Optional.of(ast)))));
	}
	
	@Test
	@CMakeTestCode(
			"cmd()\n"
			+ "cmd2()"
			)
	public void sourceFileContainingTwoCommandInvocationsWithoutArgumentsProducesFileWithTwoChildren() throws Exception {
		CMakeASTNode ast = getAST();

		assertThat(ast.getChildren().size(), equalTo(2));
		assertThat(ast.getChildren(), everyItem(instanceOf(CMakeASTNodeCommandInvocation.class)));
		assertThat(ast.getChildren(), everyItem(returnValue(CMakeASTNode::getParent, equalTo(Optional.of(ast)))));
	}

	@Test
	@CMakeTestCode("cmd(SOME UNQUOTED ARGUMENTS)")
	public void sourceFileContainingOneCommandInvocationWithArgumentsProducesFileWithOneChild() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertThat(ast.getChildren().size(), equalTo(1));
		assertThat(ast.getChildren(), everyItem(instanceOf(CMakeASTNodeCommandInvocation.class)));
		assertThat(ast.getChildren(), everyItem(returnValue(CMakeASTNode::getParent, equalTo(Optional.of(ast)))));
	}
	
}
