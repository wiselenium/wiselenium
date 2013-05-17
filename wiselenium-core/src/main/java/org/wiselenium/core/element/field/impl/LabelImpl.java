package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Label;

/**
 * Implementation of a HTML Label.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class LabelImpl extends BasicField<Label> implements Label {
	
	@Override
	public String getFor() {
		return this.getAttribute("for");
	}
	
	@Override
	public String getText() {
		return unwrapWebElement(this).getText();
	}
	
}
