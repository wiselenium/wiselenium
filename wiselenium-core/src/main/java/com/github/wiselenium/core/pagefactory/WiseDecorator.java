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

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.google.common.collect.Lists;

/**
 * Class responsible for decorating WebElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class WiseDecorator implements ExtendedSeleniumDecorator {
	
	private final ExtendedSeleniumDecoratorChain decoratorChain;
	
	
	/**
	 * @param factory The factory of the locator of the elements.
	 */
	public WiseDecorator(ElementLocatorFactory factory) {
		ExtendedSeleniumDecoratorChain extendedDefaultSeleniumDecorator = new DefaultExtendedSeleniumDecoratorChain(
			factory);
		ExtendedSeleniumDecoratorChain wiseFrameDecorator = new WiseFrameDecoratorChain(factory)
			.setNext(extendedDefaultSeleniumDecorator);
		ExtendedSeleniumDecoratorChain wiseContainerDecorator = new WiseContainerDecoratorChain(
			factory).setNext(wiseFrameDecorator);
		ExtendedSeleniumDecoratorChain wiseFieldDecorator = new WiseFieldDecoratorChain(factory)
			.setNext(wiseContainerDecorator);
		
		this.decoratorChain = wiseFieldDecorator;
	}
	
	/**
	 * Decorates a webElement.
	 * 
	 * @param <E> The type of the decorated element.
	 * @param clazz The class of the decorated element. Must be either WebElement or a type
	 * annotated with Field, Container or Frame. Will lookup for its implementation class following
	 * {@link com.github.wiselenium.core.util.ClasspathUtil#findImplementationClass(Class)} rules.
	 * @param webElement The webElement that will be decorated.
	 * @return The webElement decorated.
	 * @since 0.2.0
	 */
	public static <E> E decorateElement(Class<E> clazz, WebElement webElement) {
		WiseDecorator decorator = new WiseDecorator(new DefaultElementLocatorFactory(webElement));
		return decorator.decorate(clazz, webElement);
	}
	
	/**
	 * Decorates a list of webElements.
	 * 
	 * @param <E> The type of the decorated elements.
	 * @param clazz The class of the decorated elements. Must be either WebElement or a type
	 * annotated with Field, Container or Frame. Will lookup for its implementation class following
	 * {@link com.github.wiselenium.core.util.ClasspathUtil#findImplementationClass(Class)} rules.
	 * @param webElements The webElements that will be decorated.
	 * @return The webElements decorated.
	 * @since 0.2.0
	 */
	public static <E> List<E> decorateElements(Class<E> clazz, List<WebElement> webElements) {
		List<E> elements = Lists.newArrayList();
		for (WebElement webElement : webElements)
			elements.add(decorateElement(clazz, webElement));
		return elements;
	}
	
	@Override
	public <E> List<E> decorate(Class<E> clazz, List<WebElement> webElements) {
		return this.decoratorChain.decorate(clazz, webElements);
	}
	
	@Override
	public <E> E decorate(Class<E> clazz, WebElement webElement) {
		return this.decoratorChain.decorate(clazz, webElement);
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.decoratorChain.decorate(loader, field);
	}
	
}
