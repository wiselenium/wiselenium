package com.github.wiselenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import com.github.wiselenium.factory.decorator.WiseDecorator;
import com.google.common.collect.Lists;

/**
 * Wiselenium facade.
 *
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public final class Wiselenium {
	
	/**
	 * Finds the first element within the search context using the given mechanism. <br/>
	 * Throws a NoSuchElementException if the element couldn't be found. <br/>
	 * 
	 * @param clazz The class of the element.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context.
	 * @return The element.
	 * @since 0.1.0
	 */
	public static <E> E findElement(Class<E> clazz, By by, SearchContext searchContext) {
		WebElement webElement = searchContext.findElement(by);
		return decorateElement(clazz, webElement);
	}
	
	/**
	 * Finds all elements within the search context using the given mechanism. <br/>
	 * An empty list will be returned if the elements couldn't be found. <br/>
	 * 
	 * @param clazz The class of the elements.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context.
	 * @return The list of elements.
	 * @since 0.1.0
	 */
	public static <E> List<E> findElements(Class<E> clazz, By by, SearchContext searchContext) {
		List<WebElement> webElements = searchContext.findElements(by);
		if (webElements.isEmpty()) return Lists.newArrayList();
		
		WiseDecorator decorator = new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
		return decorator.decorate(clazz, webElements);
	}
	
	/**
	 * Decorates a webElement.
	 * 
	 * @param clazz The class of the decorated element. Must be either WebElement or a type
	 * annotated with Field, Container or Frame.
	 * @param webElement The webElement that will be decorated.
	 * @return The decorated element or null if the type isn't supported.
	 * @since 0.2.0
	 */
	public static <E> E decorateElement(Class<E> clazz, WebElement webElement) {
		WiseDecorator decorator = new WiseDecorator(new DefaultElementLocatorFactory(webElement));
		return decorator.decorate(clazz, webElement);
	}
	
	/**
	 * Decorates a list of webElements.
	 * 
	 * @param clazz The class of the decorated elements. Must be either WebElement or a type
	 * annotated with Field, Container or Frame.
	 * @param webElements The webElements that will be decorated.
	 * @return The list of decorated elements or an empty list if the type is not supported.
	 * @since 0.2.0
	 */
	public static <E> List<E> decorateElements(Class<E> clazz, List<WebElement> webElements) {
		List<E> elements = Lists.newArrayList();
		for (WebElement webElement : webElements) {
			E element = decorateElement(clazz, webElement);
			if (element != null) elements.add(element);
		}
		return elements;
	}
	
}
