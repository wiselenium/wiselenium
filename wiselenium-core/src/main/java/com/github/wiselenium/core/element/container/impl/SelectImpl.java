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
package com.github.wiselenium.core.element.container.impl;

import static com.github.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.core.element.container.Select;
import com.github.wiselenium.core.element.field.Option;

/**
 * Implementation of a HTML Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class SelectImpl extends BasicContainer<Select> implements Select {
	
	private org.openqa.selenium.support.ui.Select wrappedSelect;
	
	@FindBy(tagName = "option")
	private List<Option> options;
	
	
	@Override
	public List<Option> getOptions() {
		return this.options;
	}
	
	@Override
	public Option getSelectedOption() {
		for (Option option : this.getOptions())
			if (option.isSelected()) return option;
		return null;
	}
	
	@Override
	public String getSelectedValue() {
		return this.getWrappedSelect().getFirstSelectedOption().getAttribute("value");
	}
	
	@Override
	public String getSelectedVisibleText() {
		return this.getWrappedSelect().getFirstSelectedOption().getText();
	}
	
	@Override
	public Select selectByIndex(int index) {
		this.getWrappedSelect().selectByIndex(index);
		return this;
	}
	
	@Override
	public Select selectByValue(String value) {
		this.getWrappedSelect().selectByValue(value);
		return this;
	}
	
	@Override
	public Select selectByVisibleText(String text) {
		this.getWrappedSelect().selectByVisibleText(text);
		return this;
	}
	
	@Override
	public Select selectOption(Option option) {
		if (!option.isSelected()) option.click();
		return this;
	}
	
	private synchronized org.openqa.selenium.support.ui.Select getWrappedSelect() {
		if (this.wrappedSelect == null)
			this.wrappedSelect = new org.openqa.selenium.support.ui.Select(unwrapWebElement(this));
		return this.wrappedSelect;
	}
	
}
