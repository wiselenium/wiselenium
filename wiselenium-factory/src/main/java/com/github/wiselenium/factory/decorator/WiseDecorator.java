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
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.github.wiselenium.factory.decorator.component.WiseComponentDecorator;
import com.github.wiselenium.factory.decorator.frame.WiseFrameDecorator;

/**
 * Class responsible for decorating elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public class WiseDecorator implements ElementDecorator {
	
	private final ElementDecorator elementDecorator;
	
	/**
	 * Constructor.
	 * 
	 * @param factory The factory of the elements locator.
	 */
	public WiseDecorator(ElementLocatorFactory factory) {
		ElementDecoratorChain wiseComponentDecorator = new WiseComponentDecorator(factory);
		ElementDecoratorChain wiseFrameDecorator = new WiseFrameDecorator(factory);
		ElementDecoratorChain webElementDecorator = new WebElementDecorator(factory);
		ElementDecoratorChain nullDecorator = new NullDecorator(factory);
		
		wiseComponentDecorator.setNext(wiseFrameDecorator);
		wiseFrameDecorator.setNext(webElementDecorator);
		webElementDecorator.setNext(nullDecorator);
		
		this.elementDecorator = wiseComponentDecorator;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param decorator The object responsible for decorating the elements.
	 */
	public WiseDecorator(ElementDecorator decorator) {
		this.elementDecorator = decorator;
	}
	
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.elementDecorator.decorate(loader, field);
	}
	
	@Override
	public <E> E decorate(Class<E> clazz, WebElement webElement) {
		return this.elementDecorator.decorate(clazz, webElement);
	}
	
	@Override
	public <E> List<E> decorate(Class<E> clazz, List<WebElement> webElements) {
		return this.elementDecorator.decorate(clazz, webElements);
	}
	
}
