package org.wiselenium.core.element;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

/**
 * Basic implementation of a common Element. <br/>
 * Should be extended to reflect your own element methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The element type.
 * @since 0.0.1
 */
public class BasicElement<T extends Element<T>> implements Element<T> {
	
	@SuppressWarnings("unchecked")
	@Override
	public T and() {
		return (T) this;
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
	public String getId() {
		return this.getAttribute("id");
	}
	
	@Override
	public String getStyleClass() {
		return this.getAttribute("class");
	}
	
	@Override
	public String getTitle() {
		return this.getAttribute("title");
	}
	
	@Override
	public boolean isDisplayed() {
		return unwrapWebElement(this).isDisplayed();
	}
	
}
