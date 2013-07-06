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
package com.github.wiselenium.core;

import org.openqa.selenium.WebDriver;

/**
 * Helper class to make some objects available for the whole thread. <br/>
 * For instance, the WebDriver instance.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.2.0
 */
public final class WiseContext {
	
	static final InheritableThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL;
	
	static {
		WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<WebDriver>();
	}
	
	
	private WiseContext() {}
	
	/**
	 * Retrieves the WebDriver of the current thread.
	 * 
	 * @return The WebDriver of the current thread.
	 * @since 0.1.0
	 */
	public static WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
	
	/**
	 * Sets the WebDriver of the current thread.
	 * 
	 * @param driver The WebDriver of the current thread.
	 * @since 0.1.0
	 */
	public static void setDriver(WebDriver driver) {
		WEB_DRIVER_THREAD_LOCAL.set(driver);
	}
	
}
