package com.cthing.cmakeed.parser.tests.ast;

import static com.cthing.cmakeed.parser.tests.support.ReturnValue.returnValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeCommandInvocation;
import com.cthing.cmakeed.parser.tests.CMakeTestCode;

public class CMakeASTUnquotedArgumentTest extends CMakeASTTest {

	private static CMakeASTNodeArgument getFirstArgument(CMakeASTNode ast) {
		return getAllArguments(ast).get(0);
	}
	
	private static List<CMakeASTNodeArgument> getAllArguments(CMakeASTNode ast) {
		CMakeASTNodeCommandInvocation invocation = (CMakeASTNodeCommandInvocation) ast.getChild(0).get();
		ArrayList<CMakeASTNodeArgument> list = new ArrayList<CMakeASTNodeArgument>();
		invocation.getArguments().forEach(list::add);
		return list;
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT)")
	public void unquotedArgumentHasInvocationAsParent() throws Exception {
		CMakeASTNode ast = getAST();
		CMakeASTNodeCommandInvocation invocation = (CMakeASTNodeCommandInvocation) ast.getChild(0).get();
		
		assertThat(invocation.getArguments(), everyItem(returnValue(CMakeASTNode::getParent, equalTo(Optional.of(invocation)))));
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\;SEMICOLON)")
	public void unquotedArgumentWithEscapedSemicolonIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		CMakeASTNodeArgument argument = getFirstArgument(ast);
		
		assertThat(argument.getValue(), equalTo("ARGUMENT;SEMICOLON"));
	}
}
