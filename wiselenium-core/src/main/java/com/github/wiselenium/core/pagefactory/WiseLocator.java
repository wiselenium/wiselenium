/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import com.google.common.collect.Lists;

/**
 * Utility class to find elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public final class WiseLocator {
	
	private WiseLocator() {}
	
	/**
	 * Finds the first element within the search context using the given mechanism. <br/>
	 * Throws a NoSuchElementException if the element couldn't be found. <br/>
	 * As a utility method for the WiseQuery, it follows its correspondent method
	 * {@link com.github.wiselenium.core.WiseQuery#findElement(Class, By)} specification.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context.
	 * @return The element decorated.
	 * @since 0.1.0
	 */
	public static <E> E findElement(Class<E> clazz, By by, SearchContext searchContext) {
		WebElement webElement = searchContext.findElement(by);
		WiseDecorator decorator = new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
		return decorator.decorate(clazz, webElement);
	}
	
	/**
	 * Finds all elements within the search context using the given mechanism. <br/>
	 * An empty list will be returned if the elements couldn't be found. <br/>
	 * As a utility method for the WiseQuery, it follows its correspondent method
	 * {@link com.github.wiselenium.core.WiseQuery#findElements(Class, By)} specification.
	 * 
	 * @param <E> The type of the elements.
	 * @param clazz The class of the elements.
	 * @param by The locating mechanism to use.
	 * @param searchContext The search context.
	 * @return The elements decorated.
	 * @since 0.1.0
	 */
	public static <E> List<E> findElements(Class<E> clazz, By by, SearchContext searchContext) {
		List<WebElement> webElements = searchContext.findElements(by);
		if (webElements.isEmpty()) return Lists.newArrayList();
		WiseDecorator decorator = new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
		return decorator.decorate(clazz, webElements);
	}
	
}
