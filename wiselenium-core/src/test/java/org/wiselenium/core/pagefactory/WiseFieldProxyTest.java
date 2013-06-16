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
package org.wiselenium.core.pagefactory;

import static org.mockito.Mockito.mock;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.field.impl.TextImpl;
import org.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseFieldProxyTest extends WiseTestNG<WiseFieldProxyTest> {
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		Text proxy;
		try {
			DummyPage page = this.initElements(DummyPage.class);
			WebElement webElement = page.getText();
			proxy = WiseFieldProxy.getInstance(TextImpl.class, webElement);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.getId();
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		WiseFieldProxy.getInstance(DummyFieldWithoutNoArgConstructor.class, mock(WebElement.class));
	}
	
}
