package com.cthing.cmakeed.parser.tests.support;

import java.util.function.Function;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class ReturnValue<T, U> extends FeatureMatcher<T, U> {

	private final Function<T, U> fMethod;
	
	private ReturnValue(Function<T, U> method, Matcher<? super U> subMatcher) {
		super(subMatcher, "", "");
		fMethod = method;
	}
	
	@Override
	protected U featureValueOf(T actual) {
		return  fMethod.apply(actual);
	}

	public static <T, U>  ReturnValue<T, U> returnValue(Function<T, U> method, Matcher<? super U> subMatcher) {
		return new ReturnValue<>(method, subMatcher);
	}
	
}
