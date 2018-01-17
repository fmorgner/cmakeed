package com.cthing.cmakeed.parser.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	com.cthing.cmakeed.parser.tests.astnodes.AllTests.class,
	com.cthing.cmakeed.parser.tests.visitor.AllTests.class,
})
public class TestSuiteAll {

}
