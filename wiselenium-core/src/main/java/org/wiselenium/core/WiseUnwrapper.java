package org.wiselenium.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Utility class to unwrap WebElements out of Elements and WebDrivers out of Pages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WiseUnwrapper {
	
	private WiseUnwrapper() {}
	
	/**
	 * Unwraps the WebDriver of a page. <br/>
	 * In order to do so, the page must implement WrapsDriver. (Notice: if the page was created
	 * using the WisePageFactory with a no-arg constructor, it is in fact a proxy that implements it
	 * already.)
	 * 
	 * @param page The page.
	 * @return The WebDriver decorated by the page.
	 * @since 0.0.1
	 */
	public static WebDriver unwrapWebDriver(Object page) {
		if (page instanceof WrapsDriver) return ((WrapsDriver) page).getWrappedDriver();
		throw new WebDriverNotWrappedException(page);
	}
	
	/**
	 * Unwraps the WebElement of an Element. <br/>
	 * In order to do so, the element must implement WrapsElement. (Notice: wiselenium elements'
	 * proxies already implement it.)
	 * 
	 * @param element The element.
	 * @return The WebElement decorated by the Element.
	 * @since 0.0.1
	 */
	public static WebElement unwrapWebElement(Object element) {
		if (element instanceof WrapsElement) return ((WrapsElement) element).getWrappedElement();
		throw new WebElementNotWrappedException(element);
	}
	
}
