package org.wiselenium.core.element.field.impl;

import static java.lang.Integer.parseInt;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Text;

/**
 * Implementation of a HTML input text.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class TextImpl extends BasicField<Text> implements Text {
	
	@Override
	public Text clear() {
		unwrapWebElement(this).clear();
		return this;
	}
	
	@Override
	public Integer getMaxLength() {
		String maxlength = this.getAttribute("maxlength");
		try {
			return parseInt(maxlength);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	@Override
	public String getValue() {
		return this.getAttribute("value");
	}
	
	@Override
	public boolean isEnabled() {
		return unwrapWebElement(this).isEnabled();
	}
	
	@Override
	public boolean isReadOnly() {
		String readonly = this.getAttribute("readonly");
		if (readonly == null) return false;
		return true;
	}
	
	@Override
	public Text sendKeys(CharSequence... keysToSend) {
		unwrapWebElement(this).sendKeys(keysToSend);
		return this;
	}
	
}
