package org.wiselenium.core.element.container.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Option;

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
