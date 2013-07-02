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

import static com.github.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static com.github.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.testng.annotations.Test;

import com.github.wiselenium.core.WebDriverNotWrappedException;
import com.github.wiselenium.core.WebElementNotWrappedException;

@SuppressWarnings("javadoc")
public class WiseUnwrapperTest {
	
	@Test(expectedExceptions = WebDriverNotWrappedException.class)
	public void shouldThrowExceptionWhenNoWebDriverIsWrapped() {
		unwrapWebDriver(new Object());
	}
	
	@Test(expectedExceptions = WebElementNotWrappedException.class)
	public void shouldThrowExceptionWhenNoWebElementIsWrapped() {
		unwrapWebElement(new Object());
	}
	
	@Test
	public void shouldUnwrapDriver() {
		WrapsDriver wrapsDriver = mock(WrapsDriver.class);
		HtmlUnitDriver webDriver = new HtmlUnitDriver();
		when(wrapsDriver.getWrappedDriver()).thenReturn(webDriver);
		
		WebDriver wrappedDriver = unwrapWebDriver(wrapsDriver);
		assertNotNull(wrappedDriver);
		assertEquals(wrappedDriver, webDriver);
	}
	
	@Test
	public void shouldUnwrapElement() {
		WrapsElement wrapsElement = mock(WrapsElement.class);
		WebElement webElement = mock(WebElement.class);
		when(wrapsElement.getWrappedElement()).thenReturn(webElement);
		
		WebElement wrappedElement = unwrapWebElement(wrapsElement);
		assertNotNull(wrappedElement);
		assertEquals(wrappedElement, webElement);
	}
	
}
