package org.wiselenium.core.pagefactory.dummy;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Button;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverConstructor extends Page<DummyPageWithWebDriverConstructor> {
	
	public static final String URL = getAbsoluteFilePath("button.html");
	
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
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
