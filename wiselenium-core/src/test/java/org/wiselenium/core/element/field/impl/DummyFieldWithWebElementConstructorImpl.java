package org.wiselenium.core.element.field.impl;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.element.field.DummyFieldWithWebElementConstructor;

@SuppressWarnings("javadoc")
public class DummyFieldWithWebElementConstructorImpl implements DummyFieldWithWebElementConstructor {
	
	private final WebElement webElement;
	
	
	public DummyFieldWithWebElementConstructorImpl(WebElement webElement) {
		this.webElement = webElement;
	}
	
	@Override
	public WebElement getWrappedElement() {
		return this.webElement;
	}
	
}
