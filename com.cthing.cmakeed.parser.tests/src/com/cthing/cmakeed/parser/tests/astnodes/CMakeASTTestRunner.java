package com.cthing.cmakeed.parser.tests.astnodes;

import java.lang.reflect.Field;
import java.util.Objects;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;

import com.cthing.cmakeed.parser.tests.CMakeTestCode;

public class CMakeASTTestRunner extends BlockJUnit4ClassRunner {

	private Field fSourceTextField;
	private String fSourceText;
	
	@SuppressWarnings("unchecked")
	public CMakeASTTestRunner(Class<?> klass) throws Exception {
		super(klass);
		while(klass != null && klass != CMakeASTTest.class) {
			klass = klass.getSuperclass();
		}
		Class<CMakeASTTest> testClass = (Class<CMakeASTTest>) Objects.requireNonNull(klass);
		fSourceTextField = testClass.getDeclaredField("fSourceText");
		fSourceTextField.setAccessible(true);
		return;
	}

	@Override
	protected Object createTest() throws Exception {
		CMakeASTTest testObject = (CMakeASTTest) super.createTest();
		fSourceTextField.set(testObject, fSourceText);
		return testObject;
	}
	
	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		CMakeTestCode annotation = Objects.requireNonNull(method.getAnnotation(CMakeTestCode.class));
		fSourceText = annotation.value();
		super.runChild(method, notifier);
	}
	

}
