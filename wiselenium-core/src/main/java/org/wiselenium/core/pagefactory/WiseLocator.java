package org.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 * Utility class to find elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WiseLocator {
	
	private WiseLocator() {}
	
	/**
	 * Finds the first element within the search context using the given mechanism.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element. Must be either a WebElement or a type annotated with
	 * Field, Container or Frame.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context of the element.
	 * @return The element decorated or null if it shouldn't be decorated because the type didn't
	 * respect the parameter specification.
	 * @since 0.0.1
	 */
	public static <E> E findElement(Class<E> clazz, By by, SearchContext searchContext) {
		WebElement webElement = searchContext.findElement(by);
		return getWiseDecorator(searchContext).decorate(clazz, webElement);
	}
	
	/**
	 * Finds all elements within the search context using the given mechanism.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element. Must be either a WebElement or a type annotated with
	 * Field, Container or Frame.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context of the element.
	 * @return The elements decorated or an empty list if it shouldn't be decorated because the type
	 * didn't respect the parameter specification.
	 * @since 0.0.1
	 */
	public static <E> List<E> findElements(Class<E> clazz, By by, SearchContext searchContext) {
		List<WebElement> webElements = searchContext.findElements(by);
		return getWiseDecorator(searchContext).decorate(clazz, webElements);
	}
	
	private static WiseDecorator getWiseDecorator(SearchContext searchContext) {
		return new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
	}
	
}
