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
package com.github.wiselenium.core.annotation;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.core.annotation.AjaxElement;
import com.github.wiselenium.core.element.field.Hyperlink;
import com.github.wiselenium.core.element.field.Text;
import com.github.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class AjaxElementPage extends Page<AjaxElementPage> {
	
	public static final String URL = "http://www.google.com";
	public static final String TITLE = "Google";
	
	@FindBy(id = "gbqfq")
	private Text text;
	
	@AjaxElement
	@FindBy(xpath = "//a[contains(text(), 'Selenium - Web Browser Automation')]")
	private Hyperlink seleniumLink;
	
	
	public Hyperlink getSeleniumLink() {
		return this.seleniumLink;
	}
	
	public AjaxElementPage search(CharSequence... keysToSend) {
		this.text.sendKeys(keysToSend).and().sendKeys(Keys.ENTER);
		return this;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
