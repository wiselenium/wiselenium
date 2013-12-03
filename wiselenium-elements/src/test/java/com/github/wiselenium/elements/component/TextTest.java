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
package com.github.wiselenium.elements.component;

import static com.github.wiselenium.elements.component.page.TextPage.HIDDEN_TEXT_VALUE;
import static com.github.wiselenium.elements.component.page.TextPage.TEXT_CLICKED_MESSAGE;
import static com.github.wiselenium.elements.component.page.TextPage.TEXT_MAXLENGTH;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.elements.component.page.TextPage;
import com.github.wiselenium.factory.WisePageFactory;

@SuppressWarnings("javadoc")
public class TextTest extends TestBase {
	
	TextPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = WisePageFactory.initElements(this.driver, TextPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldClear() {
		String keysToSend = "test";
		assertTrue(this.page.getText().sendKeys(keysToSend).and().clear().and().getValue()
				.isEmpty());
	}
	
	@Test
	public void shouldClick() {
		this.page.getText().click();
		assertEquals(this.page.getMessage(), TEXT_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getText().isEnabled());
		assertFalse(this.page.getDisabledText().isEnabled());
	}
	
	@Test
	public void shouldGetMaxlength() {
		assertEquals(this.page.getText().getMaxLength(), TEXT_MAXLENGTH);
		assertNull(this.page.getReadonlyText().getMaxLength());
	}
	
	@Test
	public void shouldGetReadOnly() {
		assertFalse(this.page.getText().isReadOnly());
		assertFalse(this.page.getHiddenText().isReadOnly());
		
		assertTrue(this.page.getReadonlyText().isReadOnly());
		assertTrue(this.page.getReadonlyReadonlyText().isReadOnly());
		assertTrue(this.page.getReadonlyTrueText().isReadOnly());
		assertTrue(this.page.getReadonlyFalseText().isReadOnly());
	}
	
	@Test
	public void shouldGetValue() {
		assertTrue(this.page.getText().getValue().isEmpty());
		assertEquals(this.page.getHiddenText().getValue(), HIDDEN_TEXT_VALUE);
	}
	
	@Test
	public void shouldSendKeys() {
		String keysToSend = "test";
		assertEquals(this.page.getText().sendKeys(keysToSend).and().getValue(), keysToSend);
	}
	
}
