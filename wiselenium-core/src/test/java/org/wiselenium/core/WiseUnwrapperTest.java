package org.wiselenium.core;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;

import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class WiseUnwrapperTest {
	
	@Test(expectedExceptions = WebDriverNotWrappedException.class)
	public void shouldThrowExceptionWhenNoWebDriverIsWrapped() {
		unwrapWebDriver(new Object());
	}
	
	@Test(expectedExceptions = WebElementNotWrappedException.class)
	public void shouldThrowExceptionWhenNoWebElementIsWrapped() {
		WiseUnwrapper.unwrapWebElement(new Object());
	}
	
}
