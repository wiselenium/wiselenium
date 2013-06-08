package org.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Allows the WisePageFactory to decorate fields or webElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
interface ExtendedSeleniumDecorator extends FieldDecorator {
	
	/**
	 * Decides how to decorate the list of webElements.
	 * 
	 * @param <E> The class type.
	 * @param clazz The class that will decorate each webElement of the list. If the class is not
	 * concrete, will lookup for its implementation matching the pattern
	 * classPackage.{impl}.className{Impl} yet. A more sofisticated search is planned to be
	 * implemented on the future.
	 * @param webElements The list of webElements that may be decorated.
	 * @return The list with the webElements decorated or empty if they shouldn't be decorated.
	 */
	<E> List<E> decorate(Class<E> clazz, List<WebElement> webElements);
	
	/**
	 * Decides how to decorate the webElement.
	 * 
	 * @param <E> The class type.
	 * @param clazz The class that will decorate the webElement. If the class is not concrete, will
	 * lookup for its implementation matching the pattern classPackage.{impl}.className{Impl} yet. A
	 * more sofisticated search is planned to be implemented on the future.
	 * @param webElement The webElement that may be decorated.
	 * @return The decorated webElement or null if it shouldn't be decorated.
	 */
	<E> E decorate(Class<E> clazz, WebElement webElement);
	
}
