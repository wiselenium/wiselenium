package org.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.testng.collections.Lists;

/**
 * Utility class to find elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WiseLocator {
	
	private WiseLocator() {}
	
	/**
	 * Finds the first element within the search context using the given mechanism. <br/>
	 * Throws a NoSuchElementException if the element couldn't be found. <br/>
	 * As a utility method for the WiseQuery, it follows its correspondent method
	 * {@link org.wiselenium.core.WiseQuery#findElement(Class, By)} specification.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context.
	 * @return The element decorated.
	 * @since 0.0.1
	 */
	public static <E> E findElement(Class<E> clazz, By by, SearchContext searchContext) {
		WebElement webElement = searchContext.findElement(by);
		return getWiseDecorator(searchContext).decorate(clazz, webElement);
	}
	
	/**
	 * Finds all elements within the search context using the given mechanism. <br/>
	 * An empty list will be returned if the elements couldn't be found. <br/>
	 * As a utility method for the WiseQuery, it follows its correspondent method
	 * {@link org.wiselenium.core.WiseQuery#findElements(Class, By)} specification.
	 * 
	 * @param <E> The type of the elements.
	 * @param clazz The class of the elements.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context.
	 * @return The elements decorated.
	 * @since 0.0.1
	 */
	public static <E> List<E> findElements(Class<E> clazz, By by, SearchContext searchContext) {
		List<WebElement> webElements = searchContext.findElements(by);
		if (webElements.isEmpty()) return Lists.newArrayList();
		return getWiseDecorator(searchContext).decorate(clazz, webElements);
	}
	
	private static WiseDecorator getWiseDecorator(SearchContext searchContext) {
		return new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
	}
	
}
