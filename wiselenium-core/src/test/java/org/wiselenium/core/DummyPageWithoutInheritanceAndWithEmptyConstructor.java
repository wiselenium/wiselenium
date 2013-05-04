package org.wiselenium.core;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithWebElementInjectThroughConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithoutInheritanceAndWithEmptyConstructor {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithWebElementInjectThroughConstructor dummy;
	
	
	public WebElement getDummyElementWebElement() {
		return this.dummy.getWebElement();
	}
	
	public WebDriver getWebDriver() {
		return unwrapWebDriver(this);
	}
	
	public WebDriver getWrappedDriver() {
		// doesn't matter, because this page will be proxied and the proxy getWrappedDriver method
		// will be called instead of this
		return null;
	}
	
}
