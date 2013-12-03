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
package com.github.wiselenium.factory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.github.wiselenium.factory.annotation.AjaxElement;
import com.github.wiselenium.factory.decorator.WiseDecorator;
import com.github.wiselenium.factory.exception.ElementInitializationException;
import com.github.wiselenium.factory.exception.PageInstantiationException;

/**
 * Utility class to create Pages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public final class WisePageFactory {
	
	public WisePageFactory() {}
	
	/**
	 * Instantiates an object of the given class and initializes its elements. <br/>
	 * This method will attempt to instantiate the object using preferably a constructor
	 * that takes a WebDriver instance as its only argument or falling back on a no-arg constructor.
	 * An exception will be thrown if the object cannot be instantiated. <br/>
	 * 
	 * @param driver The driver that will be used to look up the object elements.
	 * @param clazz The class to be instantiated.
	 * @return An instance of the class with its elements proxied.
	 * @since 0.1.0
	 */
	public static <T> T initElements(WebDriver driver, Class<T> clazz) {
		T instance = instantiatePage(driver, clazz);
		return initElements(driver, instance);
	}
	
	/**
	 * Initializes the elements of an existing object.
	 * 
	 * @param searchContext The context that will be used to look up the elements.
	 * @param instance The instance whose fields will be initialized.
	 * @return The instance with its elements initialized.
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
	
	private static <T> T instantiatePage(WebDriver driver, Class<T> clazz) {
		try {
			return clazz.getConstructor(WebDriver.class).newInstance(driver);
		} catch (Exception e1) {
			try {
				T page = clazz.newInstance();
				RootInjector.rootDriver(driver, page);
				return page;
			} catch (Exception e2) {
				throw new PageInstantiationException(clazz);
			}
		}
	}
	
	private static void proxyElements(SearchContext searchContext, Object instance,
			Class<?> instanceHierarchyClass) {
		
		Field[] fields = instanceHierarchyClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isSynthetic()) continue;
			FieldDecorator decorator = initDecorator(searchContext, field);
			Object value = decorator.decorate(instance.getClass().getClassLoader(), field);
			if (value != null) {
				try {
					field.setAccessible(true);
					field.set(instance, value);
				} catch (IllegalAccessException e) {
					throw new ElementInitializationException(field, instance.getClass(), e);
				}
			}
		}
	}
	
	private static FieldDecorator initDecorator(SearchContext searchContext, Field field) {
		FieldDecorator decorator;
		AjaxElement ajaxElementAnnotation = field.getAnnotation(AjaxElement.class);
		if (ajaxElementAnnotation != null) {
			int timeout = ajaxElementAnnotation.timeOutInSeconds();
			decorator = new WiseDecorator(new AjaxElementLocatorFactory(searchContext, timeout));
		} else {
			decorator = new WiseDecorator(new DefaultElementLocatorFactory(searchContext));
		}
		return decorator;
	}
	
}
