package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Text;

/**
 * Implementation of a HTML input text;
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class TextImpl implements Text {
	
	@Override
	public Text and() {
		return this;
	}
	
	@Override
	public Text clear() {
		unwrapWebElement(this).clear();
		return this;
	}
	
	@Override
	public Text click() {
		unwrapWebElement(this).click();
		return null;
	}
	
	@Override
	public Text doubleClick() {
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
	
	@Override
	public Text sendKeys(CharSequence... keysToSend) {
		unwrapWebElement(this).sendKeys(keysToSend);
		return this;
	}
	
}
