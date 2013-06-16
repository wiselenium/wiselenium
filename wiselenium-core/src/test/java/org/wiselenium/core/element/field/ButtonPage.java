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
package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class ButtonPage extends Page<ButtonPage> {
	
	public static final String BUTTON_DOUBLE_CLICKED_MESSAGE = "button doubleclicked";
	public static final String BUTTON_CLICKED_MESSAGE = "button clicked";
	public static final String BUTTON_VALUE = "button";
	public static final String HIDDEN_BUTTON_VALUE = "hiddenButton";
	public static final String URL = getAbsolutePath("button.html");
	public static final String TITLE = "page for button tests";
	
	private Button button;
	private Button hiddenButton;
	private Button resetButton;
	private Button submitButton;
	private Button disabledButton;
	private WebElement message;
	
	
	public Button getButton() {
		return this.button;
	}
	
	public Button getDisabledButton() {
		return this.disabledButton;
	}
	
	public Button getHiddenButton() {
		return this.hiddenButton;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public Button getResetButton() {
		return this.resetButton;
	}
	
	public Button getSubmitButton() {
		return this.submitButton;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
