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

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_RADIOBUTTONS;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_SELECT1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.pagefactory.WiseLocator;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseLocatorTest extends WiseTestNG<WiseLocatorTest> {
	
	private DummyPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(DummyPage.class).and().get();
	}
	
	@Test
	public void shouldFindElement() {
		Select select = WiseLocator.findElement(Select.class, BY_SELECT1,
			this.page.getWrappedDriver());
		assertNotNull(select);
		assertNotNull(unwrapWebElement(select));
	}
	
	@SuppressWarnings("null")
	@Test
	public void shouldFindElements() {
		List<Radiobutton> radiobuttons = WiseLocator.findElements(Radiobutton.class,
			BY_RADIOBUTTONS, this.page.getWrappedDriver());
		assertTrue(radiobuttons != null && !radiobuttons.isEmpty());
		for (Radiobutton radiobutton : radiobuttons)
			assertNotNull(unwrapWebElement(radiobutton));
	}
	
	@Test
	public void shouldReturnEmptyListWhenElementsAreNotFound() {
		List<Select> selects = WiseLocator.findElements(Select.class, By.id("inexistent"),
			this.page.getWrappedDriver());
		assertTrue(selects != null && selects.isEmpty());
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldThrowExceptionWhenElementIsntFound() {
		WiseLocator.findElement(Select.class, By.id("inexistent"), this.page.getWrappedDriver());
	}
	
}
