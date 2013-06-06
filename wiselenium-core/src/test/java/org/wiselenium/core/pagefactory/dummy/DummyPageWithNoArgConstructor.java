package org.wiselenium.core.pagefactory.dummy;

import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Button;

@SuppressWarnings("javadoc")
public class DummyPageWithNoArgConstructor {
	
	public static final String URL = getAbsolutePath("button.html");
	
	@FindBy(id = "button")
	private Button dummy;
	
	
	public Button getDummyField() {
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
