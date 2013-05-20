package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Option;

/**
 * Implementation of a HTML Select Option.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class OptionImpl extends BasicField<Option> implements Option {
	
	@Override
	public String getValue() {
		return this.getAttribute("value");
	}
	
	@Override
	public String getVisibleText() {
		return unwrapWebElement(this).getText();
	}
	
	@Override
	public boolean isSelected() {
		return unwrapWebElement(this).isSelected();
	}
	
}
