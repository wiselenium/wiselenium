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
package com.github.wiselenium;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.element.DummyField;

@SuppressWarnings("javadoc")
public class WiseleniumTest extends TestBase {
	
	private static final String DUMMY_PAGE = TestResourceFinder.getAbsolutePath("dummy.html");
	private static final By BY_SELECT1 = By.id("select1");
	private static final By BY_RADIOBUTTON = By.name("sex");
	
	@BeforeMethod
	public void initPage() {
		this.driver.get(DUMMY_PAGE);
	}
	
	@Test
	public void shouldFindElement() {
		DummyField select = Wiselenium.findElement(DummyField.class, BY_SELECT1, this.driver);
		assertNotNull(select);
	}
	
	@SuppressWarnings("null")
	@Test
	public void shouldFindElements() {
		List<DummyField> elements = Wiselenium.findElements(
				DummyField.class, BY_RADIOBUTTON, this.driver);
		
		assertTrue(elements != null && !elements.isEmpty());
		for (DummyField element : elements) {
			assertNotNull(element);
		}
	}
	
	@Test
	public void shouldReturnEmptyListWhenElementsAreNotFound() {
		List<DummyField> selects = Wiselenium.findElements(
				DummyField.class, By.id("inexistent"), this.driver);
		assertTrue(selects != null && selects.isEmpty());
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldThrowExceptionWhenElementIsntFound() {
		Wiselenium.findElement(DummyField.class, By.id("inexistent"), this.driver);
	}
	
}
