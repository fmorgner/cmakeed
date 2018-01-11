package com.cthing.cmakeed.parser;

public class CMakeParserError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CMakeParserError(String reason) {
		super(reason);
	}
	
}
