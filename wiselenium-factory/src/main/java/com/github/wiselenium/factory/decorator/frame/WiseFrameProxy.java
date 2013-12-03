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
package com.github.wiselenium.factory.decorator.frame;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.WiseContext;
import com.github.wiselenium.factory.WisePageFactory;

/**
 * The wiselenium proxy for frames.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public final class WiseFrameProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	private List<String> parentFramePath;
	private boolean elementsInitialized;
	private boolean active;
	
	private WiseFrameProxy(WebElement webElement) {
		this.wrappedElement = webElement;
	}
	
	@SuppressWarnings("unchecked")
	static <E> E getInstance(Class<E> implementationClass,WebElement webElement) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implementationClass);
		e.setCallback(new WiseFrameProxy(webElement));
		return (E) e.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
			throws Throwable { // NOSONAR because it's an overridden method
		
		boolean activator = false;
		
		List<String> currentPath = null;
		if (!this.active) {
			activator = true;
			this.active = true;
			currentPath = FrameSwitcher.getCurrentFramePath();
			this.switchToFrame();
			this.initElements(obj);
		}
		
		try {
			return proxy.invokeSuper(obj, args);
		} finally {
			if (activator && this.active) {
				this.active = false;
				FrameSwitcher.switchToFramePath(currentPath);
			}
		}
	}
	
	private void switchToFrame() {
		FrameSwitcher.switchToFramePath(this.getCurrentFramePath());
		WiseContext.getDriver().switchTo().frame(this.wrappedElement);
	}
	
	private synchronized List<String> getCurrentFramePath() {
		if (this.parentFramePath == null) {
			this.parentFramePath = FrameSwitcher.getCurrentFramePath();
		}
		return this.parentFramePath;
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(WiseContext.getDriver(), obj);
			this.elementsInitialized = true;
		}
	}
	
}
