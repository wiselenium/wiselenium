package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Radiobutton;

/**
 * Implementation of a HTML Radio button.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class RadiobuttonImpl extends BasicField<Radiobutton> implements Radiobutton {
	
	@Override
	public Radiobutton check() {
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
	
}
