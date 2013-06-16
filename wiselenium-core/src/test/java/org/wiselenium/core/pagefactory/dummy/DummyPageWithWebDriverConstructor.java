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

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Button;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverConstructor extends Page<DummyPageWithWebDriverConstructor> {
	
	public static final String URL = getAbsolutePath("button.html");
	
	@FindBy(id = "button")
	private Button dummy;
	
	
	public DummyPageWithWebDriverConstructor(WebDriver driver) {
		super(driver);
	}
	
	public Button getDummyElement() {
		return this.dummy;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
