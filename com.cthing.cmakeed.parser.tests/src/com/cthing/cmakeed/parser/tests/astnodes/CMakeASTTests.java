package com.cthing.cmakeed.parser.tests.astnodes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CMakeASTFileTest.class,
	CMakeASTUnquotedArgumentTest.class,
})
public class CMakeASTTests {

}
