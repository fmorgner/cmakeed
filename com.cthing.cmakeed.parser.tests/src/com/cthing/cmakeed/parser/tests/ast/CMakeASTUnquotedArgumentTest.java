package com.cthing.cmakeed.parser.tests.ast;

import static com.cthing.cmakeed.parser.tests.support.ReturnValue.returnValue;
import static org.hamcrest.CoreMatchers.instanceOf;
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
import com.cthing.cmakeed.parser.ast.CMakeASTNodeVariableReference;
import com.cthing.cmakeed.parser.tests.CMakeTestCode;

public class CMakeASTUnquotedArgumentTest extends CMakeASTTest {


	private static List<CMakeASTNodeArgument> getAllArguments(CMakeASTNode ast) {
		CMakeASTNodeCommandInvocation invocation = (CMakeASTNodeCommandInvocation) ast.getChild(0).get();
		ArrayList<CMakeASTNodeArgument> list = new ArrayList<CMakeASTNodeArgument>();
		invocation.getArguments().forEach(list::add);
		return list;
	}
	
	private static CMakeASTNodeArgument assertSingleArgumentWithValue(CMakeASTNode ast, String value) throws Exception {
		final List<CMakeASTNodeArgument> arguments = getAllArguments(ast);
		assertThat(arguments.size(), equalTo(1));
		assertThat(arguments, everyItem(returnValue(CMakeASTNodeArgument::getValue, equalTo(value))));
		return arguments.get(0);
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

		assertSingleArgumentWithValue(ast, "ARGUMENT;SEMICOLON");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\(ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityLeftParenthesisIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT(ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\)ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityRightParenthesisIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT)ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\#ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityPoundIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT#ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\\"ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityDoubleQuoteIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT\"ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\ ESCAPED)")
	public void unquotedArgumentWithEscapedIdentitySpaceIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\\\ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityBackslashIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT\\ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\$ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityDollarIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT$ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\@ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityAtIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT@ESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(ARGUMENT\\^ESCAPED)")
	public void unquotedArgumentWithEscapedIdentityCarretIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT^ESCAPED");
	}

	@Test
	@CMakeTestCode("cmd(ARGUMENT\\rESCAPED)")
	public void unquotedArgumentWithEscapedEncodedCarriageReturnIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT\rESCAPED");
	}

	@Test
	@CMakeTestCode("cmd(ARGUMENT\\nESCAPED)")
	public void unquotedArgumentWithEscapedEncodedNewlineIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT\nESCAPED");
	}

	@Test
	@CMakeTestCode("cmd(ARGUMENT\\tESCAPED)")
	public void unquotedArgumentWithEscapedEncodedTabIsConsideredAsOneArgument() throws Exception {
		CMakeASTNode ast = getAST();
		
		assertSingleArgumentWithValue(ast, "ARGUMENT\tESCAPED");
	}
	
	@Test
	@CMakeTestCode("cmd(${VAR})")
	public void unquotedArgumentConsistingOfVariableReference() throws Exception {
		CMakeASTNode ast = getAST();
		
		CMakeASTNodeArgument argument = assertSingleArgumentWithValue(ast, "${VAR}");
		assertThat(argument.getChildren().size(), equalTo(1));
		assertThat(argument.getChildren(), everyItem(instanceOf(CMakeASTNodeVariableReference.class)));
	}
	
}
