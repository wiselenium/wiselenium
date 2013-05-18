package org.wiselenium.core.element.field.impl;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.element.field.DummyFieldWithEmptyConstructor;

@SuppressWarnings("javadoc")
public class DummyFieldWithEmptyConstructorImpl implements DummyFieldWithEmptyConstructor {
	
	@Override
	public WebElement getWrappedElement() {
		// doesn't matter, because this page will be proxied and the proxy getWrappedElement method
		// will be called instead of this
		return null;
	}
	
	@Override
	public void throwIllegalArgumetException() {
		throw new IllegalArgumentException();
	}
	
}
