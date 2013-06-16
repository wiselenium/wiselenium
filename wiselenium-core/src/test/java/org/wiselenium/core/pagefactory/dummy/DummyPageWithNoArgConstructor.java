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
package org.wiselenium.core.pagefactory.dummy;

import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Button;

@SuppressWarnings("javadoc")
public class DummyPageWithNoArgConstructor {
	
	public static final String URL = getAbsolutePath("button.html");
	
	@FindBy(id = "button")
	private Button dummy;
	
	
	public Button getDummyField() {
		return this.dummy;
	}
	
	public WebDriver getWrappedDriver() {
		// doesn't matter, because this page will be proxied and the proxy getWrappedDriver method
		// will be called instead of this
		return null;
	}
	
	public void throwIllegalArgumentException() {
		throw new IllegalArgumentException();
	}
	
}
