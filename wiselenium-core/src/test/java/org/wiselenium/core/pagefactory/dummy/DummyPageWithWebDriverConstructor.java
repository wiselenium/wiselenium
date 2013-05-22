package org.wiselenium.core.pagefactory.dummy;

import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Button;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverConstructor extends Page<DummyPageWithWebDriverConstructor> {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private Button dummy;
	
	
	public DummyPageWithWebDriverConstructor(WebDriver driver) {
		super(driver);
	}
	
	public Button getDummyElement() {
		return this.dummy;
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
