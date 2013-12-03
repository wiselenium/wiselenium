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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.elements.component.Checkbox;
import com.github.wiselenium.elements.component.page.CheckboxPage;
import com.github.wiselenium.factory.WisePageFactory;

@SuppressWarnings("javadoc")
public class CheckboxTest extends TestBase {
	
	CheckboxPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = WisePageFactory.initElements(this.driver, CheckboxPage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldCheck() {
		Checkbox checkbox = this.page.getCheckbox();
		checkbox.check();
		assertTrue(checkbox.isChecked());
		checkbox.check();
		assertTrue(checkbox.isChecked());
	}
	
	@Test
	public void shouldClick() {
		Checkbox checkbox = this.page.getCheckbox();
		boolean checkedConditionbeforeClick = checkbox.isChecked();
		checkbox.click();
		assertNotEquals(checkbox.isChecked(), checkedConditionbeforeClick);
	}
	
	@Test
	public void shouldGetChecked() {
		assertFalse(this.page.getCheckbox().isChecked());
		assertTrue(this.page.getCheckbox().check().and().isChecked());
		assertFalse(this.page.getCheckbox().uncheck().and().isChecked());
		
		assertTrue(this.page.getCheckedCheckbox().isChecked());
		assertTrue(this.page.getCheckedCheckedCheckbox().isChecked());
		assertTrue(this.page.getCheckedTrueCheckbox().isChecked());
		assertTrue(this.page.getCheckedFalseCheckbox().isChecked());
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getCheckbox().isEnabled());
		assertFalse(this.page.getDisabledCheckbox().isEnabled());
	}
	
	@Test
	public void shouldUncheck() {
		Checkbox checkbox = this.page.getCheckbox();
		checkbox.uncheck();
		assertFalse(checkbox.isChecked());
		checkbox.uncheck();
		assertFalse(checkbox.isChecked());
	}
	
}
