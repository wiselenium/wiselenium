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

import com.github.wiselenium.TestResourceFinder;
import com.github.wiselenium.elements.component.Checkbox;
import com.github.wiselenium.elements.page.Page;

@SuppressWarnings("javadoc")
public class CheckboxPage extends Page {
	
	public static final String URL = TestResourceFinder.getAbsolutePath("checkbox.html");
	public static final String TITLE = "page for checkbox tests";
	
	private Checkbox checkbox;
	private Checkbox disabledCheckbox;
	private Checkbox checkedCheckbox;
	private Checkbox checkedCheckedCheckbox;
	private Checkbox checkedTrueCheckbox;
	private Checkbox checkedFalseCheckbox;
	
	
	public Checkbox getCheckbox() {
		return this.checkbox;
	}
	
	public Checkbox getCheckedCheckbox() {
		return this.checkedCheckbox;
	}
	
	public Checkbox getCheckedCheckedCheckbox() {
		return this.checkedCheckedCheckbox;
	}
	
	public Checkbox getCheckedFalseCheckbox() {
		return this.checkedFalseCheckbox;
	}
	
	public Checkbox getCheckedTrueCheckbox() {
		return this.checkedTrueCheckbox;
	}
	
	public Checkbox getDisabledCheckbox() {
		return this.disabledCheckbox;
	}
	
	@Override
	public void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getCurrentUrl(), URL);
	}
	
	@Override
	public void load() {
		this.get(URL);
	}
	
}
