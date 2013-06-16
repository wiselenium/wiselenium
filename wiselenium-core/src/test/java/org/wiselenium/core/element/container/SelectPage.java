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
package org.wiselenium.core.element.container;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class SelectPage extends Page<SelectPage> {
	
	public static final String SELECT_CLICKED_MESSAGE = "select clicked";
	public static final String SELECT_FOR = "text";
	public static final String SELECT_TEXT = "select text";
	public static final String URL = getAbsolutePath("select.html");
	public static final String TITLE = "page for select tests";
	public static final String FIRST_OPTION_VALUE = "value1";
	public static final String SECOND_OPTION_VALUE = "value2";
	public static final String THIRD_OPTION_VALUE = "value3";
	public static final String FIRST_OPTION_TEXT = "text1";
	public static final String SECOND_OPTION_TEXT = "text2";
	public static final String THIRD_OPTION_TEXT = "text3";
	public static final String SELECT_TITLE = "select title";
	public static final String SELECT_CLASS = "any";
	
	private Select select;
	private Select hiddenSelect;
	private MultiSelect multiselect;
	private WebElement message;
	
	
	public Select getHiddenSelect() {
		return this.hiddenSelect;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public MultiSelect getMultiselect() {
		return this.multiselect;
	}
	
	public Select getSelect() {
		return this.select;
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
