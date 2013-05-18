package org.wiselenium.core.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithEmptyConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithoutInheritanceAndWithEmptyConstructor {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithEmptyConstructor dummy;
	
	
	public DummyFieldWithEmptyConstructor getDummyField() {
		return this.dummy;
	}
	
	public WebDriver getWrappedDriver() {
		// doesn't matter, because this page will be proxied and the proxy getWrappedDriver method
		// will be called instead of this
		return null;
	}
	
	public void throwIllegalArgumentException() {
		throw new IllegalArgumentException();
	}
	
}
