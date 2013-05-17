package org.wiselenium.core.element.field.impl;

import static java.lang.Integer.parseInt;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Text;

/**
 * Implementation of a HTML input text.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class TextImpl extends BasicField<Text> implements Text {
	
	@Override
	public Text clear() {
		unwrapWebElement(this).clear();
		return this;
	}
	
	@Override
	public Integer getMaxLength() {
		String maxlength = unwrapWebElement(this).getAttribute("maxlength");
		try {
			return parseInt(maxlength);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	@Override
	public String getValue() {
		return unwrapWebElement(this).getAttribute("value");
	}
	
	@Override
	public boolean isReadOnly() {
		String readonly = unwrapWebElement(this).getAttribute("readonly");
		if (readonly == null) return false;
		return true;
	}
	
	@Override
	public Text sendKeys(CharSequence... keysToSend) {
		unwrapWebElement(this).sendKeys(keysToSend);
		return this;
	}
	
}
