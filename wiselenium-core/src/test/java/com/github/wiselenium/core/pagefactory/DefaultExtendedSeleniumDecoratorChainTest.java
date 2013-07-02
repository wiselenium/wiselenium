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

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.wiselenium.core.element.field.Text;
import com.github.wiselenium.core.pagefactory.DefaultExtendedSeleniumDecoratorChain;
import com.github.wiselenium.core.pagefactory.dummy.DummyPage;
import com.github.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class DefaultExtendedSeleniumDecoratorChainTest extends
	WiseTestNG<DefaultExtendedSeleniumDecoratorChainTest> {
	
	private DummyPage page;
	private DefaultExtendedSeleniumDecoratorChain decorator;
	
	
	@BeforeClass
	public void initDecorator() {
		this.page = this.initElements(DummyPage.class);
		this.decorator = new DefaultExtendedSeleniumDecoratorChain(
			new DefaultElementLocatorFactory(this.getDriver()));
	}
	
	@Test
	public void shouldDecorate() {
		WebElement element = this.decorator.decorate(WebElement.class, this.page.getText());
		Assert.assertNotNull(element);
	}
	
	@Test
	public void shouldReturnEmptyListOnDecorateElementsWhenItShouldntDecorateAndTheresNoNextDecorator() {
		List<Text> list = this.decorator.decorate(Text.class, this.page.getRadiobuttons());
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void shouldReturnNullOnDecorateElementWhenItShouldntDecorateAndTheresNoNextDecorator() {
		Text element = this.decorator.decorate(Text.class, this.page.getText());
		Assert.assertNull(element);
	}
	
}
