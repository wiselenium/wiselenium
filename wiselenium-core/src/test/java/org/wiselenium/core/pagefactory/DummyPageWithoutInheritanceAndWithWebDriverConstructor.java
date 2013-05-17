package org.wiselenium.core.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithWebElementConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithoutInheritanceAndWithWebDriverConstructor {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithWebElementConstructor dummy;
	
	private final WebDriver driver;
	
	
	public DummyPageWithoutInheritanceAndWithWebDriverConstructor(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getDummyElementWebElement() {
		return this.dummy.getWrappedElement();
	}
	
	public WebDriver getWrappedDriver() {
		return this.driver;
	}
	
}
