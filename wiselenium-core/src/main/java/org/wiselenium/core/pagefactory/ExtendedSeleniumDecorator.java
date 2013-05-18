package org.wiselenium.core.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Allows the WisePageFactory to decorate fields or webelements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
interface ExtendedSeleniumDecorator extends FieldDecorator {
	
	/**
	 * Decides how to decorate the webElement.
	 * 
	 * @param clazz The class that will decorate the webelement.
	 * @param webElement The webelement that may be decorated.
	 * @return The decorated webElement or null if it shouldn't be decorated.
	 */
	Object decorate(Class<?> clazz, WebElement webElement);
	
}
