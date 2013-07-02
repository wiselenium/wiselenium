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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.collections.Lists;

import com.github.wiselenium.core.element.container.MultiSelect;
import com.github.wiselenium.core.element.field.Option;

/**
 * Implementation of a HTML Multiple Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class MultiSelectImpl extends BasicContainer<MultiSelect> implements MultiSelect {
	
	private Select wrappedSelect;
	
	@FindBy(tagName = "option")
	private List<Option> options;
	
	
	@Override
	public MultiSelect deselectAll() {
		this.getWrappedSelect().deselectAll();
		return this;
	}
	
	@Override
	public MultiSelect deselectByIndex(int... indexes) {
		for (int i : indexes)
			this.getWrappedSelect().deselectByIndex(i);
		return this;
	}
	
	@Override
	public MultiSelect deselectByValue(String... values) {
		for (String v : values)
			this.getWrappedSelect().deselectByValue(v);
		return this;
	}
	
	@Override
	public MultiSelect deselectByVisibleText(String... texts) {
		for (String t : texts)
			this.getWrappedSelect().deselectByVisibleText(t);
		return this;
	}
	
	@Override
	public MultiSelect deselectOptions(Option... options) {
		for (Option option : options)
			if (option.isSelected()) option.click();
		return this;
	}
	
	@Override
	public List<Option> getOptions() {
		return this.options;
	}
	
	@Override
	public List<Option> getSelectedOptions() {
		List<Option> selectedOptions = Lists.newArrayList();
		for (Option option : this.options)
			if (option.isSelected()) selectedOptions.add(option);
		return selectedOptions;
	}
	
	@Override
	public List<String> getSelectedValues() {
		List<String> values = Lists.newArrayList();
		List<WebElement> selectedOptions = this.getWrappedSelect().getAllSelectedOptions();
		for (WebElement option : selectedOptions)
			values.add(option.getAttribute("value"));
		return values;
	}
	
	@Override
	public List<String> getSelectedVisibleTexts() {
		List<String> texts = Lists.newArrayList();
		List<WebElement> selectedOptions = this.getWrappedSelect().getAllSelectedOptions();
		for (WebElement option : selectedOptions)
			texts.add(option.getText());
		return texts;
	}
	
	@Override
	public MultiSelect selectAll() {
		List<WebElement> selectOptions = this.getWrappedSelect().getOptions();
		for (WebElement option : selectOptions)
			if (!option.isSelected()) option.click();
		return this;
	}
	
	@Override
	public MultiSelect selectByIndex(int... indexes) {
		for (int i : indexes)
			this.getWrappedSelect().selectByIndex(i);
		return this;
	}
	
	@Override
	public MultiSelect selectByValue(String... values) {
		for (String v : values)
			this.getWrappedSelect().selectByValue(v);
		return this;
	}
	
	@Override
	public MultiSelect selectByVisibleText(String... texts) {
		for (String t : texts)
			this.getWrappedSelect().selectByVisibleText(t);
		return this;
	}
	
	@Override
	public MultiSelect selectOptions(Option... options) {
		for (Option option : options)
			if (!option.isSelected()) option.click();
		return this;
	}
	
	private synchronized Select getWrappedSelect() {
		if (this.wrappedSelect == null) this.wrappedSelect = new Select(unwrapWebElement(this));
		return this.wrappedSelect;
	}
	
}
