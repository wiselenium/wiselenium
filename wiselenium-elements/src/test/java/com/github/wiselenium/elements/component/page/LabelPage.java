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
package com.github.wiselenium.elements.component.page;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.TestResourceFinder;
import com.github.wiselenium.elements.component.Label;
import com.github.wiselenium.elements.page.Page;

@SuppressWarnings("javadoc")
public class LabelPage extends Page {
	
	public static final String LABEL_CLICKED_MESSAGE = "label clicked";
	public static final String LABEL_FOR = "text";
	public static final String LABEL_TEXT = "label text";
	public static final String URL = TestResourceFinder.getAbsolutePath("label.html");
	public static final String TITLE = "page for label tests";
	
	private Label label;
	private Label hiddenLabel;
	private WebElement message;
	
	
	public Label getHiddenLabel() {
		return this.hiddenLabel;
	}
	
	public Label getLabel() {
		return this.label;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
