package org.wiselenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithWebElementConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverConstructor extends Page<DummyPageWithWebDriverConstructor> {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithWebElementConstructor dummy;
	
	
	public DummyPageWithWebDriverConstructor(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getDummyElementWebElement() {
		return this.dummy.getWrappedElement();
	}
	
}
