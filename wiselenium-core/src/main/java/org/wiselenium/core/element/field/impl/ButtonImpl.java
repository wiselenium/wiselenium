package org.wiselenium.core.element.field.impl;

import org.wiselenium.core.element.field.Button;

/**
 * Implementation of a HTML Button.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class ButtonImpl extends BasicField<Button> implements Button {
	
	@Override
	public String getType() {
		return this.getAttribute("type");
	}
	
	@Override
	public String getValue() {
		return this.getAttribute("value");
	}
	
}
