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
package com.github.wiselenium.elements.page;

import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waits for specific conditions.
 *
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public interface WiseWait {
	
	/**
	 * Creates a WebDriverWait.
	 * 
	 * @param timeOutInSeconds The timeout in seconds.
	 * @return A WebDriverWait.
	 * @since 0.3.0
	 */
	WebDriverWait waitFor(long timeOutInSeconds);
	
	/**
	 * Creates a WebDriverWait.
	 * 
	 * @param timeOutInSeconds The timeout in seconds.
	 * @param sleepInMillis The polling wait time.
	 * @return A WebDriverWait.
	 * @since 0.3.0
	 */
	WebDriverWait waitFor(long timeOutInSeconds, long sleepInMillis);
	
}
