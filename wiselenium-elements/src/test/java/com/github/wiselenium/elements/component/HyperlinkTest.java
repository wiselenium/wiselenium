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

import static com.github.wiselenium.elements.component.page.HyperlinkPage.HYPERLINK_CLICKED_MESSAGE;
import static com.github.wiselenium.elements.component.page.HyperlinkPage.HYPERLINK_HREF;
import static com.github.wiselenium.elements.component.page.HyperlinkPage.HYPERLINK_TARGET;
import static com.github.wiselenium.elements.component.page.HyperlinkPage.HYPERLINK_TEXT;
import static com.github.wiselenium.elements.component.page.HyperlinkPage.URL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.elements.component.page.HyperlinkPage;
import com.github.wiselenium.factory.WisePageFactory;

@SuppressWarnings("javadoc")
public class HyperlinkTest extends TestBase {
	
	HyperlinkPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = WisePageFactory.initElements(this.driver, HyperlinkPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldClick() {
		this.page.getHyperlink().click();
		assertEquals(this.page.getMessage(), HYPERLINK_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetHref() {
		assertTrue(this.page.getHyperlink().getHref().endsWith(URL + HYPERLINK_HREF));
	}
	
	@Test
	public void shouldGetTarget() {
		assertEquals(this.page.getHyperlink().getTarget(), HYPERLINK_TARGET);
	}
	
	@Test
	public void shouldGetText() {
		assertEquals(this.page.getHyperlink().getText(), HYPERLINK_TEXT);
	}
	
}
