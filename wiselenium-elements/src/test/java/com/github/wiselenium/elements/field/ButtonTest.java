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
package com.github.wiselenium.elements.field;

import static com.github.wiselenium.elements.field.page.ButtonPage.BUTTON_CLICKED_MESSAGE;
import static com.github.wiselenium.elements.field.page.ButtonPage.BUTTON_VALUE;
import static com.github.wiselenium.elements.field.page.ButtonPage.HIDDEN_BUTTON_VALUE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.elements.field.page.ButtonPage;
import com.github.wiselenium.factory.WisePageFactory;

@SuppressWarnings("javadoc")
public class ButtonTest extends TestBase {
	
	ButtonPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = WisePageFactory.initElements(this.driver, ButtonPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldClick() {
		this.page.getButton().click();
		assertEquals(this.page.getMessage(), BUTTON_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getButton().isEnabled());
		assertFalse(this.page.getDisabledButton().isEnabled());
	}
	
	@Test
	public void shouldGetType() {
		assertEquals(this.page.getButton().getType(), "button");
		assertEquals(this.page.getResetButton().getType(), "reset");
		assertEquals(this.page.getSubmitButton().getType(), "submit");
	}
	
	@Test
	public void shouldGetValue() {
		assertEquals(this.page.getButton().getValue(), BUTTON_VALUE);
		assertEquals(this.page.getHiddenButton().getValue(), HIDDEN_BUTTON_VALUE);
	}
	
}
