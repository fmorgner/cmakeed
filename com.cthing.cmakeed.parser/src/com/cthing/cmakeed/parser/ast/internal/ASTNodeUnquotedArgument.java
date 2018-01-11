package com.cthing.cmakeed.parser.ast.internal;

import com.cthing.cmakeed.parser.llparser.CMakeParser.Unquoted_elementContext;

public class ASTNodeUnquotedArgument extends ASTNodeArgument {

	protected ASTNodeUnquotedArgument(Unquoted_elementContext context) {
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
	}

}
