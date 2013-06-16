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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Option;
import org.wiselenium.core.element.field.TextPage;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithFinalField;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithWebDriverConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithoutProperConstructor;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends WiseTestNG<WisePageFactoryTest> {
	
	@Test
	public void shouldCreatePageWithNoArgConstructorAndInitElements() {
		this.getDriver().get(DummyPageWithNoArgConstructor.URL);
		
		DummyPageWithNoArgConstructor page = this.initElements(DummyPageWithNoArgConstructor.class);
		
		assertNotNull(unwrapWebDriver(page));
		assertNotNull(page.getWrappedDriver());
		assertNotNull(unwrapWebElement(page.getDummyField()));
	}
	
	@Test
	public void shouldCreatePageWithWebDriverConstructorAndInitElements() {
		DummyPageWithWebDriverConstructor page = this
			.initElements(DummyPageWithWebDriverConstructor.class).and().get();
		
		assertNotNull(unwrapWebDriver(page));
		assertNotNull(page.getWrappedDriver());
		assertNotNull(unwrapWebElement(page.getDummyElement()));
	}
	
	@Test
	public void shouldInitElementsLazily() {
		this.getDriver().get(TextPage.URL);
		DummyPage page = this.initElements(DummyPage.class).and().get();
		
		Select select1 = page.getSelect1();
		assertNotNull(select1);
		
		List<Option> options = select1.getOptions();
		assertTrue(options != null && !options.isEmpty());
		if (options != null) for (Option option : options)
			assertNotNull(option);
	}
	
	@Test
	public void shouldInitElementsOfInstance() {
		DummyPageWithWebDriverConstructor page = new DummyPageWithWebDriverConstructor(
			this.getDriver());
		this.initElements(page);
		assertNotNull(page.getDummyElement());
		assertNotNull(unwrapWebElement(page.getDummyElement()));
	}
	
	@Test
	public void shouldInitWebElements() {
		DummyPage page = this.initElements(DummyPage.class).and().get();
		WebElement text = page.getText();
		assertNotNull(text);
		List<WebElement> sexRadiobuttons = page.getRadiobuttons();
		assertFalse(sexRadiobuttons.isEmpty());
	}
	
	@Test(expectedExceptions = PageInitializationException.class)
	public void shouldThrowExceptionWhileCreatingPageWithFinalField() {
		this.getDriver().get(DummyPageWithFinalField.URL);
		this.initElements(DummyPageWithFinalField.class);
	}
	
	@Test(expectedExceptions = PageInstantiationException.class)
	public void shouldThrowExceptionWhileInstantiatingPageWithoutProperConstructor() {
		this.initElements(DummyPageWithoutProperConstructor.class);
	}
	
}
