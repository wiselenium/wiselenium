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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.testng.collections.Lists;

/**
 * The wiselenium proxy for a list of elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseElementListProxy<E> implements MethodInterceptor {
	
	private final Class<E> clazz;
	private final List<WebElement> webElements;
	private List<E> elements;
	private final ElementLocatorFactory locatorFactory;
	
	
	private WiseElementListProxy(Class<E> clazz, List<WebElement> webElements,
		ElementLocatorFactory factory) {
		this.clazz = clazz;
		this.webElements = webElements;
		this.locatorFactory = factory;
	}
	
	@SuppressWarnings("unchecked")
	static <E> List<E> getInstance(Class<E> clazz, List<WebElement> webElements,
		ElementLocatorFactory factory) {
		Enhancer e = new Enhancer();
		e.setInterfaces(new Class[] { List.class });
		e.setCallback(new WiseElementListProxy<E>(clazz, webElements, factory));
		return (List<E>) e.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		try {
			this.initElements();
			return method.invoke(this.elements, args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}
	
	private synchronized void initElements() {
		if (this.elements == null) {
			this.elements = Lists.newArrayList();
			for (WebElement webElement : this.webElements) {
				E element = new WiseDecorator(this.locatorFactory).decorate(this.clazz, webElement);
				this.elements.add(element);
			}
		}
	}
	
}
