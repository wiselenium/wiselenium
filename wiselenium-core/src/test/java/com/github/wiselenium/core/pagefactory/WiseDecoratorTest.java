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
package com.github.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.core.WiseUnwrapper;
import com.github.wiselenium.core.element.container.Select;
import com.github.wiselenium.core.element.field.ButtonPage;
import com.github.wiselenium.core.element.field.Radiobutton;
import com.github.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import com.github.wiselenium.core.pagefactory.dummy.DummyPage;
import com.github.wiselenium.core.test.WiseTestNG;
import com.github.wiselenium.core.test.annotation.Page;

@SuppressWarnings("javadoc")
public class WiseDecoratorTest extends WiseTestNG<WiseDecoratorTest> {
	
	@Page
	private DummyPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page.get();
	}
	
	@Test
	public void shouldDecorateWebElement() {
		WebElement webElement = this.findElement(WebElement.class, DummyPage.BY_SELECT1);
		Select select = WiseDecorator.decorateElement(Select.class, webElement);
		assertNotNull(select);
	}
	
	@Test
	public void shouldDecorateWebElementEvenWhenNotFoundAtTheTime() {
		WebElement webElement = this.findElement(WebElement.class, DummyPage.BY_SELECT1);
		this.page.get(ButtonPage.URL);
		Select select = WiseDecorator.decorateElement(Select.class, webElement);
		assertNotNull(select);
	}
	
	@Test
	public void shouldDecorateWebElements() {
		List<WebElement> webElements = this.findElements(WebElement.class,
			DummyPage.BY_RADIOBUTTONS);
		List<Radiobutton> radiobuttons = WiseDecorator.decorateElements(Radiobutton.class,
			webElements);
		assertTrue(radiobuttons != null && !radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldDecorateWebElementsEvenWhenNotFoundAtTheTime() {
		List<WebElement> webElements = this.findElements(WebElement.class,
			DummyPage.BY_RADIOBUTTONS);
		this.page.get(ButtonPage.URL);
		List<Radiobutton> radiobuttons = WiseDecorator.decorateElements(Radiobutton.class,
			webElements);
		assertNotNull(radiobuttons);
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenDecoratingTypeWithoutNoArgConstructor() {
		WiseDecorator.decorateElement(DummyFieldWithoutNoArgConstructor.class,
			WiseUnwrapper.unwrapWebElement(this.page.getText()));
	}
	
}