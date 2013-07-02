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
package com.github.wiselenium.core.element.container;

import static com.github.wiselenium.core.element.container.SelectPage.FIRST_OPTION_TEXT;
import static com.github.wiselenium.core.element.container.SelectPage.FIRST_OPTION_VALUE;
import static com.github.wiselenium.core.element.container.SelectPage.SECOND_OPTION_TEXT;
import static com.github.wiselenium.core.element.container.SelectPage.SECOND_OPTION_VALUE;
import static com.github.wiselenium.core.element.container.SelectPage.THIRD_OPTION_TEXT;
import static com.github.wiselenium.core.element.container.SelectPage.THIRD_OPTION_VALUE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.core.element.container.Select;
import com.github.wiselenium.core.element.field.Option;
import com.github.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class SelectTest extends WiseTestNG<SelectTest> {
	
	SelectPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(SelectPage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldGetOptions() {
		List<Option> options = this.page.getSelect().getOptions();
		assertNotNull(options);
		assertTrue(!options.isEmpty());
	}
	
	@Test
	public void shouldGetSelectedValue() {
		assertEquals(this.page.getSelect().getSelectedValue(), FIRST_OPTION_VALUE);
	}
	
	@Test
	public void shouldGetSelectedVisibleText() {
		assertEquals(this.page.getSelect().getSelectedVisibleText(), FIRST_OPTION_TEXT);
	}
	
	@SuppressWarnings("null")
	@Test
	public void shouldSelectAndGetSelectedOption() {
		Select select = this.page.getSelect();
		List<Option> options = select.getOptions();
		assertTrue(options != null && !options.isEmpty());
		
		String[] values = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE, THIRD_OPTION_VALUE };
		String[] texts = { FIRST_OPTION_TEXT, SECOND_OPTION_TEXT, THIRD_OPTION_TEXT };
		
		for (int i = 0; i < options.size(); i++) {
			Option option = options.get(i);
			select.selectOption(option);
			assertTrue(option.isSelected());
			Option selectedOption = select.getSelectedOption();
			assertEquals(selectedOption.getValue(), values[i]);
			assertEquals(selectedOption.getVisibleText(), texts[i]);
		}
	}
	
	@Test
	public void shouldSelectByIndex() {
		Select select = this.page.getSelect();
		assertEquals(select.getSelectedValue(), FIRST_OPTION_VALUE);
		
		select.selectByIndex(1);
		assertEquals(select.getSelectedValue(), SECOND_OPTION_VALUE);
	}
	
	@Test
	public void shouldSelectByValue() {
		Select select = this.page.getSelect();
		assertEquals(select.getSelectedValue(), FIRST_OPTION_VALUE);
		
		select.selectByValue(SECOND_OPTION_VALUE);
		assertEquals(select.getSelectedValue(), SECOND_OPTION_VALUE);
	}
	
	@Test
	public void shouldSelectByVisibleText() {
		Select select = this.page.getSelect();
		assertEquals(select.getSelectedVisibleText(), FIRST_OPTION_TEXT);
		
		select.selectByVisibleText(THIRD_OPTION_TEXT);
		assertEquals(select.getSelectedVisibleText(), THIRD_OPTION_TEXT);
	}
	
}
