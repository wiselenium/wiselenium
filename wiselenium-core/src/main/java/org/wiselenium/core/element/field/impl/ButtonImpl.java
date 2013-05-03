package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Button;

/**
 * Implemention of a HTML Button.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class ButtonImpl implements Button {
	
	@Override
	public Button and() {
		return this;
	}
	
	@Override
	public Button click() {
		unwrapWebElement(this).click();
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Button doubleClick() {
		// TODO will need a webdriver reference
		return null;
	}
	
	@Override
	public String getAttribute(String name) {
		return unwrapWebElement(this).getAttribute(name);
	}
	
	@Override
	public String getCssValue(String propertyName) {
		return unwrapWebElement(this).getCssValue(propertyName);
	}
	
	@Override
	public String getValue() {
		return unwrapWebElement(this).getAttribute("value");
	}
	
	@Override
	public boolean isDisplayed() {
		return unwrapWebElement(this).isDisplayed();
	}
	
}
