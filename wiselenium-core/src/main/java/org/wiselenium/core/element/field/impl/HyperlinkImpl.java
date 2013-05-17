package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.field.Hyperlink;

/**
 * Implementation of a HTML Hyperlink.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class HyperlinkImpl extends BasicField<Hyperlink> implements Hyperlink {
	
	@Override
	public String getHref() {
		return this.getAttribute("href");
	}
	
	@Override
	public String getTarget() {
		return this.getAttribute("target");
	}
	
	@Override
	public String getText() {
		return unwrapWebElement(this).getText();
	}
	
}
