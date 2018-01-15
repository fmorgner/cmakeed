package com.cthing.cmakeed.parser.ast;

import java.util.Collection;

public interface CMakeASTNodeEvaluatedArgument extends CMakeASTNodeArgument {

	public Collection<CMakeASTNodeVariableReference> getVariableReferences();
	
}
