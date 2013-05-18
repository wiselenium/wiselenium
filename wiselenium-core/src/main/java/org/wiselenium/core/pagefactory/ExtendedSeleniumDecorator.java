package org.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Allows the WisePageFactory to decorate fields or webelements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
interface ExtendedSeleniumDecorator extends FieldDecorator {
	
	// TODO make use of the generics of the class to return the proper type already
	
	/**
	 * Decides how to decorate the list of webelements.
	 * 
	 * @param clazz The class that will decorate each webelement of the list.
	 * @param webElements The list of webelements that may be decorated.
	 * @return The list with the webElements decorated or empty if they shouldn't be decorated.
	 */
	Object decorate(Class<?> clazz, List<WebElement> webElements);
	
	/**
	 * Decides how to decorate the webElement.
	 * 
	 * @param clazz The class that will decorate the webelement.
	 * @param webElement The webelement that may be decorated.
	 * @return The decorated webElement or null if it shouldn't be decorated.
	 */
	Object decorate(Class<?> clazz, WebElement webElement);
	
}
