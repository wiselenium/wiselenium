package org.wiselenium.core.element.field.impl;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.element.field.DummyFieldWithWebElementInjectThroughConstructor;

@SuppressWarnings("javadoc")
public class DummyFieldWithWebElementInjectThroughConstructorImpl implements DummyFieldWithWebElementInjectThroughConstructor {
	
	private final WebElement webElement;
	
	
	public DummyFieldWithWebElementInjectThroughConstructorImpl(WebElement webElement) {
		this.webElement = webElement;
	}
	
	@Override
	public WebElement getWebElement() {
		return this.webElement;
	}
	
}
