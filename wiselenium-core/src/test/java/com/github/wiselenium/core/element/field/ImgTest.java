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
package com.github.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class ImgTest extends WiseTestNG<ImgTest> {
	
	ImgPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(ImgPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldClick() {
		this.page.getImg().click();
		assertEquals(this.page.getMessage(), ImgPage.IMG_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAlt() {
		assertEquals(this.page.getImg().getAlt(), ImgPage.IMG_ALT);
	}
	
	@Test
	public void shouldGetSrc() {
		assertTrue(this.page.getImg().getSrc().endsWith(ImgPage.IMG_SRC));
	}
	
}
