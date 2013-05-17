package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

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
	
	@Override
	protected void isLoaded() {
		assertTrue(this.getWrappedDriver().getCurrentUrl().endsWith(URL));
	}
	
	@Override
	protected void load() {
		this.get(getAbsoluteFilePath(URL));
	}
	
}
