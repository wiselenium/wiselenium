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
package org.wiselenium.core.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.wiselenium.core.annotation.AjaxElement;

/**
 * Utility class to create Pages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public final class WisePageFactory {
	
	private WisePageFactory() {}
	
	/**
	 * As {@link #initElements(WebDriver, Class)} but will only replace the element fields of an
	 * already instantiated Page Object.
	 * 
	 * @param <T> The type of the instance.
	 * @param searchContext The context that will be used to look up the elements.
	 * @param instance The instance whose fields should be proxied.
	 * @return The instance with its element fields proxied.
	 * @since 0.1.0
	 */
	public static <T> T initElements(SearchContext searchContext, T instance) {
		Class<?> currentInstanceHierarchyClass = instance.getClass();
		while (currentInstanceHierarchyClass != Object.class) {
			proxyElements(searchContext, instance, currentInstanceHierarchyClass);
			currentInstanceHierarchyClass = currentInstanceHierarchyClass.getSuperclass();
		}
		
		return instance;
	}
	
	/**
	 * Instantiates an instance of the given class, and set a lazy proxy for each of its element
	 * fields (WebElement, List&ltWebElement&gt or any type annotated with Field, Container or
	 * Frame). <br/>
	 * It assumes the element field name as the HTML element's "id" or "name". To change how the
	 * element is located, use the FindBy annotation. <br/>
	 * This method will attempt to instantiate the class given to it, using preferably a constructor
	 * that takes a WebDriver instance as its only argument or falling back on a no-arg constructor.
	 * An exception will be thrown if the class cannot be instantiated. <br/>
	 * In case the no-arg constructor is used, the page itself is proxied to wrap the WebDriver,
	 * which can be unwrapped using the WiseUnrwapper.
	 * 
	 * @param <T> The class type that will be initialized.
	 * @param driver The driver that will be used to look up the elements of the object.
	 * @param clazz The class to be initialized.
	 * @return An instance of the class with its element fields proxied.
	 * @since 0.1.0
	 */
	public static <T> T initElements(WebDriver driver, Class<T> clazz) {
		T instance = instantiatePage(driver, clazz);
		return initElements(driver, instance);
	}
	
	private static FieldDecorator instantiateDecorator(SearchContext searchContext, Field field) {
		FieldDecorator decorator;
		AjaxElement ajaxElementAnnotation = field.getAnnotation(AjaxElement.class);
		if (ajaxElementAnnotation != null) {
			int timeout = ajaxElementAnnotation.timeOutInSeconds();
			decorator = new WiseDecorator(new AjaxElementLocatorFactory(searchContext, timeout));
		} else decorator = new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
		return decorator;
	}
	
	private static <T> T instantiatePage(WebDriver driver, Class<T> clazz) {
		try {
			return instantiatePageWithWebDriverConstructor(driver, clazz);
		} catch (ClassWithoutConstructorThatTakesWebDriverException e1) {
			try {
				return instantiatePageWithEmptyConstructor(driver, clazz);
			} catch (ClassWithoutNoArgConstructorException e2) {
				throw new PageInstantiationException(clazz); // NOSONAR it won't need the stacktrace
			}
		}
	}
	
	private static <T> T instantiatePageWithEmptyConstructor(WebDriver driver, Class<T> clazz) {
		return WisePageProxy.getInstance(driver, clazz);
	}
	
	private static <T> T instantiatePageWithWebDriverConstructor(WebDriver driver, Class<T> clazz) {
		try {
			return clazz.getConstructor(WebDriver.class).newInstance(driver);
		} catch (Exception e) {
			throw new ClassWithoutConstructorThatTakesWebDriverException(clazz, e);
		}
	}
	
	private static void proxyElements(SearchContext searchContext, Object instance,
		Class<?> instanceHierarchyClass) {
		
		Field[] fields = instanceHierarchyClass.getDeclaredFields();
		for (Field field : fields) {
			FieldDecorator decorator = instantiateDecorator(searchContext, field);
			Object value = decorator.decorate(instance.getClass().getClassLoader(), field);
			if (value != null) try {
				field.setAccessible(true);
				field.set(instance, value);
			} catch (IllegalAccessException e) {
				throw new PageInitializationException(instance.getClass(), e);
			}
		}
	}
	
}
