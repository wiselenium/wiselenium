package org.wiselenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithWebElementInjectThroughConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverInjectedThoughConstructor {
	
	public static final String URL = "button.html";
	
	private final WebDriver driver;
	
	@FindBy(id = "buttonId")
	private DummyFieldWithWebElementInjectThroughConstructor dummy;
	
	
	public DummyPageWithWebDriverInjectedThoughConstructor(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getDummyElementWebElement() {
		return this.dummy.getWebElement();
	}
	
	public WebDriver getPageWebDriver() {
		return this.driver;
	}
	
}
