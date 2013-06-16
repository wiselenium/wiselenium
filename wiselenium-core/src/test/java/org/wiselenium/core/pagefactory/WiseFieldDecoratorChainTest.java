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

import java.util.List;

import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseFieldDecoratorChainTest extends WiseTestNG<WiseFieldDecoratorChainTest> {
	
	private DummyPage page;
	private WiseFieldDecoratorChain decorator;
	
	
	@BeforeClass
	public void initDecorator() {
		this.page = this.initElements(DummyPage.class);
		this.decorator = new WiseFieldDecoratorChain(new DefaultElementLocatorFactory(
			this.getDriver()));
	}
	
	@Test
	public void shouldDecorate() {
		Text element = this.decorator.decorate(Text.class, this.page.getText());
		Assert.assertNotNull(element);
	}
	
	@Test
	public void shouldReturnEmptyListOnDecorateElementsWhenItShouldntDecorateAndTheresNoNextDecorator() {
		List<Select> list = this.decorator.decorate(Select.class, this.page.getRadiobuttons());
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void shouldReturnNullOnDecorateElementWhenItShouldntDecorateAndTheresNoNextDecorator() {
		Select element = this.decorator.decorate(Select.class, this.page.getText());
		Assert.assertNull(element);
	}
	
}
