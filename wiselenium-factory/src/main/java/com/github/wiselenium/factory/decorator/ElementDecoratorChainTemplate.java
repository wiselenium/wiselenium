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
package com.github.wiselenium.factory.decorator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.github.wiselenium.factory.annotation.Root;

public abstract class ElementDecoratorChainTemplate extends DefaultFieldDecorator
implements ElementDecoratorChain {
	
	private ElementDecorator nextDecoratorInChain;
	
	public ElementDecoratorChainTemplate(ElementLocatorFactory factory) {
		super(factory);
	}
	
	
	protected abstract <E> boolean shouldDecorate(Class<E> clazz);
	
	protected abstract <E> E decorateElement(Class<E> clazz, WebElement webElement);
	
	protected abstract <E> List<E> decorateElements(Class<E> clazz, List<WebElement> webElements);
	
	
	@Override
	public <E> List<E> decorate(Class<E> clazz, List<WebElement> webElements) {
		if (!this.shouldDecorate(clazz)) return this.nextDecoratorInChain.decorate(clazz, webElements);
		return this.decorateElements(clazz, webElements);
	}
	
	@Override
	public <E> E decorate(Class<E> clazz, WebElement webElement) {
		if (!this.shouldDecorate(clazz)) return this.nextDecoratorInChain.decorate(clazz, webElement);
		return this.decorateElement(clazz, webElement);
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (field.isAnnotationPresent(Root.class)) return null;
		if (!this.shouldDecorate(field)) return this.nextDecoratorInChain.decorate(loader, field);
		
		ElementLocator locator = this.factory.createLocator(field);
		if (locator == null) return null;
		return this.decorateField(loader, field, locator);
	}
	
	private boolean shouldDecorate(Field field) {
		if (this.shouldDecorate(field.getType()) || this.isDecoratableList(field)) return true;
		return false;
	}
	
	private Object decorateField(ClassLoader loader, Field field, ElementLocator locator) {
		if (this.isDecoratableList(field)) {
			List<WebElement> webElements = this.proxyForListLocator(loader, locator);
			Class<?> listType = (Class<?>) ((ParameterizedType) field.getGenericType())
					.getActualTypeArguments()[0];
			return this.decorate(listType, webElements);
		}
		WebElement webElement = this.proxyForLocator(loader, locator);
		return this.decorate(field.getType(), webElement);
	}
	
	private boolean isDecoratableList(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) return false;
		
		// Type erasure in Java isn't complete. Attempt to discover the generic type of the list.
		Type genericType = field.getGenericType();
		if (!(genericType instanceof ParameterizedType)) return false;
		
		Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
		
		if (!this.shouldDecorate((Class<?>) listType)) return false;
		
		if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null)
			return false;
		
		return true;
	}
	
	@Override
	public void setNext(ElementDecorator decorator) {
		this.nextDecoratorInChain = decorator;
	}
	
}
