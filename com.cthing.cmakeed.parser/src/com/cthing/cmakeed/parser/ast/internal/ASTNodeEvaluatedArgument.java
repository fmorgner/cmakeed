package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;

import com.cthing.cmakeed.parser.ast.CMakeASTNodeEvaluatedArgument;
import com.cthing.cmakeed.parser.ast.CMakeASTNodeVariableReference;
import com.cthing.cmakeed.parser.llparser.CMakeParser.VariableReferenceContext;

public class ASTNodeEvaluatedArgument extends ASTNodeArgument implements CMakeASTNodeEvaluatedArgument {

	private final List<ASTNodeVariableReference> fVariableReferences = new ArrayList<>();
	
	protected ASTNodeEvaluatedArgument(ParserRuleContext context) {
		super(context);
		
		String value = context.getText();
		StringBuilder builder = new StringBuilder();

		for(int pos = 0; pos < value.length(); ++pos) {
			char c = value.charAt(pos);
			
			if(value.charAt(pos) == '\\') {
				c = value.charAt(++pos);
				if("()#\\\" $@^;".contains("" + c)) {
					builder.append(c);
				} else {
					switch(c) {
					case 'r':
						builder.append('\r');
						break;
					case 'n':
						builder.append('\n');
						break;
					case 't':
						builder.append('\t');
						break;
					}
				}
				continue;
			}
			
			builder.append(value.charAt(pos));
		}
		
		fValue = builder.toString();
		
		context.getRuleContexts(VariableReferenceContext.class).stream()
			.map(ASTNodeVariableReference::new)
			.forEach(v -> v.setParent(this));
	}

	@Override
	protected void add(ASTNode node) {
		super.add(node);
		if(node instanceof ASTNodeVariableReference) {
			fVariableReferences.add((ASTNodeVariableReference) node);
		}
	}
	
	@Override
	public Collection<CMakeASTNodeVariableReference> getVariableReferences() {
		return fVariableReferences.stream().collect(Collectors.toList());
	}

}
