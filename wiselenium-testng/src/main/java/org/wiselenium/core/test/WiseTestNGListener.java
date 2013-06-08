package org.wiselenium.core.test;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * A Method Listener that performs some operations depending on the test result. <br/>
 * 1) On a test failure, saves a screenshot in a file with name equals to
 * getScreenShotPath()testClass-methodName.png.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class WiseTestNGListener extends TestListenerAdapter {
	
	@Override
	public void onTestFailure(ITestResult tr) {
		Object testInstance = tr.getInstance();
		if (testInstance instanceof WiseTestNG<?>) {
			String fileName = testInstance.getClass().getSimpleName() + "-"
				+ tr.getMethod().getMethodName();
			((WiseTestNG<?>) testInstance).takeScreenShot(fileName);
		}
	}
	
}
