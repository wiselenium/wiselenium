package org.wiselenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithWebElementInjectThroughConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithoutInheritanceAndWithWebDriverInjectedThoughConstructor {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithWebElementInjectThroughConstructor dummy;
	
	private final WebDriver driver;
	
	
	public DummyPageWithoutInheritanceAndWithWebDriverInjectedThoughConstructor(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getDummyElementWebElement() {
		return this.dummy.getWebElement();
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}
	
}
