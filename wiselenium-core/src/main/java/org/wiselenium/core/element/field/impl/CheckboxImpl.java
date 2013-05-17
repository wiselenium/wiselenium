package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Checkbox;

/**
 * Implementation of a HTML Checkbox.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class CheckboxImpl extends BasicField<Checkbox> implements Checkbox {
	
	@Override
	public Checkbox check() {
		if (!this.isChecked()) this.click();
		return this;
	}
	
	@Override
	public boolean isChecked() {
		String checked = this.getAttribute("checked");
		if (checked == null) return false;
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return unwrapWebElement(this).isEnabled();
	}
	
	@Override
	public Checkbox uncheck() {
		if (this.isChecked()) this.click();
		return this;
	}
	
}
