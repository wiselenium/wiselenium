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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class RadiobuttonTest extends WiseTestNG<RadiobuttonTest> {
	
	RadiobuttonPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(RadiobuttonPage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldCheck() {
		Radiobutton radiobutton = this.page.getRadiobutton();
		assertFalse(radiobutton.isChecked());
		
		radiobutton.check();
		assertTrue(radiobutton.isChecked());
		radiobutton.check();
		assertTrue(radiobutton.isChecked());
	}
	
	@Test
	public void shouldClick() {
		Radiobutton radiobutton = this.page.getRadiobutton();
		boolean checkedConditionbeforeClick = radiobutton.isChecked();
		radiobutton.click();
		assertNotEquals(radiobutton.isChecked(), checkedConditionbeforeClick);
	}
	
	@Test
	public void shouldGetChecked() {
		assertFalse(this.page.getRadiobutton().isChecked());
		assertTrue(this.page.getRadiobutton().check().and().isChecked());
		assertFalse(this.page.getDisabledRadiobutton().isChecked());
		
		assertTrue(this.page.getCheckedRadiobutton().isChecked());
		assertTrue(this.page.getCheckedCheckedRadiobutton().isChecked());
		assertTrue(this.page.getCheckedTrueRadiobutton().isChecked());
		assertTrue(this.page.getCheckedFalseRadiobutton().isChecked());
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getRadiobutton().isEnabled());
		assertFalse(this.page.getDisabledRadiobutton().isEnabled());
	}
	
}
