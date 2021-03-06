/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.wiselenium.testng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * A Method Listener that performs some operations depending on the test result. <br/>
 * 1) On a test failure, saves a screenshot in a file with name equals to
 * {getTestFailureScreenShotPath()}/{testClass}_{methodName}_{currentTimestamp}.png.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public class WiseTestListener extends TestListenerAdapter {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
	
	@Override
	public void onTestFailure(ITestResult tr) {
		Object testInstance = tr.getInstance();
		if (testInstance instanceof WiseTest) {
			String fileName = String.format("%s/%s_%s_%s.png",
					((WiseTest) testInstance).getTestFailureScreenShotPath(),
					testInstance.getClass().getSimpleName(),
					tr.getMethod().getMethodName(),
					DATE_FORMAT.format(Calendar.getInstance().getTime()));
			((WiseTest) testInstance).takeScreenShot(fileName);
		}
	}
	
}
