package com.cthing.cmakeed.parser.tests.visitor;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeBracketArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeCommandInvocation;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeFile;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeQuotedArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeUnquotedArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeVariableReference;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.tests.CMakeASTTest;
import com.cthing.cmakeed.parser.tests.CMakeTestCode;

public class CMakeASTVisitorOrderTest extends CMakeASTTest {

	private TraversalRecorder fRecoder = null;
	
	private class TraversalRecorder implements CMakeASTVisitor {
		private final StringBuilder fBuffer = new StringBuilder();
		
		public String getTraversal() {
			return fBuffer.toString();
		}

		@Override
		public Decision visit(CMakeASTNodeBracketArgument argument) {
			fBuffer.append(":ba:");
			return CMakeASTVisitor.super.visit(argument);
		}

		@Override
		public Decision visit(CMakeASTNodeCommandInvocation argument) {
			fBuffer.append(":ci:");
			return CMakeASTVisitor.super.visit(argument);
		}
		
		@Override
		public Decision visit(CMakeASTNodeFile file) {
			fBuffer.append(":f:");
			return CMakeASTVisitor.super.visit(file);
		}
		
		@Override
		public Decision visit(CMakeASTNodeQuotedArgument argument) {
			fBuffer.append(":qa:");
			return CMakeASTVisitor.super.visit(argument);
		}
		
		@Override
		public Decision visit(CMakeASTNodeUnquotedArgument argument) {
			fBuffer.append(":ua:");
			return CMakeASTVisitor.super.visit(argument);
		}
		
		@Override
		public Decision visit(CMakeASTNodeVariableReference variable) {
			fBuffer.append(":vr:");
			return CMakeASTVisitor.super.visit(variable);
		}
	}

	@Before
	public void setUp() {
		fRecoder = new TraversalRecorder();
	}
	
	@Test
	@CMakeTestCode("")
	public void visitingAnEmptyFileOnlyCallsTheFileVisitMethod() throws Exception {
		CMakeASTNode ast = getAST().get();
		ast.accept(fRecoder);

		assertThat(fRecoder.getTraversal(), equalTo(":f:"));
	}
	
}
