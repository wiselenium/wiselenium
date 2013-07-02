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
package com.github.wiselenium.core.test;

import static com.github.wiselenium.core.test.Driver.CHROME;
import static com.github.wiselenium.core.test.Driver.FIREFOX;
import static com.github.wiselenium.core.test.Driver.IE32;
import static com.github.wiselenium.core.test.Driver.IE64;
import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class DriverTest {
	
	@Test
	public void shouldOpenChrome() {
		WebDriver driver = CHROME.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldOpenFirefox() {
		WebDriver driver = FIREFOX.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldOpenIE32() {
		WebDriver driver = IE32.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldOpenIE64() {
		WebDriver driver = IE64.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
}
