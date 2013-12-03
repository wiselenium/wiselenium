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

import static com.github.wiselenium.elements.component.page.ButtonPage.BUTTON_CLICKED_MESSAGE;
import static com.github.wiselenium.elements.component.page.ButtonPage.BUTTON_DOUBLE_CLICKED_MESSAGE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.elements.component.Button;
import com.github.wiselenium.elements.component.page.ButtonPage;
import com.github.wiselenium.factory.WisePageFactory;

@SuppressWarnings("javadoc")
public class BasicSimpleComponentTest extends TestBase {
	
	ButtonPage page;
	
	@BeforeMethod
	public void initPage() {
		this.page = WisePageFactory.initElements(this.driver, ButtonPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Button button = this.page.getButton();
		assertEquals(button, button.and());
	}
	
	@Test
	public void shouldClick() {
		this.page.getButton().click();
		assertEquals(this.page.getMessage(), BUTTON_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldDoubleClick() {
		this.page.getButton().doubleClick();
		assertEquals(this.page.getMessage(), BUTTON_DOUBLE_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getButton().getAttribute("id"));
		assertNotNull(this.page.getHiddenButton().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getButton().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getHiddenButton().getCssValue("visibility"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getButton().isDisplayed());
		assertFalse(this.page.getHiddenButton().isDisplayed());
	}
	
	@Test
	public void shouldGetId() {
		assertFalse(this.page.getButton().getId().isEmpty());
	}
	
	@Test
	public void shouldGetStyleClass() {
		assertFalse(this.page.getButton().getStyleClass().isEmpty());
		assertTrue(this.page.getHiddenButton().getStyleClass().isEmpty());
	}
	
	@Test
	public void shouldGetTitle() {
		assertFalse(this.page.getButton().getTitle().isEmpty());
		assertTrue(this.page.getHiddenButton().getTitle().isEmpty());
	}
	
}
