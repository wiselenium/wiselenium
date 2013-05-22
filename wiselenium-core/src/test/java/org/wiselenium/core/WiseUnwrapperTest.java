package org.wiselenium.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class WiseUnwrapperTest {
	
	@Test(expectedExceptions = WebDriverNotWrappedException.class)
	public void shouldThrowExceptionWhenNoWebDriverIsWrapped() {
		unwrapWebDriver(new Object());
	}
	
	@Test(expectedExceptions = WebElementNotWrappedException.class)
	public void shouldThrowExceptionWhenNoWebElementIsWrapped() {
		unwrapWebElement(new Object());
	}
	
	@Test
	public void shouldUnwrapDriver() {
		WrapsDriver wrapsDriver = mock(WrapsDriver.class);
		HtmlUnitDriver webDriver = new HtmlUnitDriver();
		when(wrapsDriver.getWrappedDriver()).thenReturn(webDriver);
		
		WebDriver wrappedDriver = unwrapWebDriver(wrapsDriver);
		assertNotNull(wrappedDriver);
		assertEquals(wrappedDriver, webDriver);
	}
	
	@Test
	public void shouldUnwrapElement() {
		WrapsElement wrapsElement = mock(WrapsElement.class);
		WebElement webElement = mock(WebElement.class);
		when(wrapsElement.getWrappedElement()).thenReturn(webElement);
		
		WebElement wrappedElement = unwrapWebElement(wrapsElement);
		assertNotNull(wrappedElement);
		assertEquals(wrappedElement, webElement);
	}
	
}
