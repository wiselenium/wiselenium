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
package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.util.FrameUtil.getCurrentFramePath;
import static org.wiselenium.core.util.FrameUtil.switchToFrame;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.util.FrameUtil;

/**
 * The wiselenium proxy for frame inner webElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameInnerWebElementProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	private final List<String> framePath;
	
	
	private WiseFrameInnerWebElementProxy(WebElement element) {
		this.wrappedElement = element;
		this.framePath = FrameUtil.getCurrentFramePath();
	}
	
	static WebElement getInstance(WebElement element) {
		Enhancer e = new Enhancer();
		e.setInterfaces(element.getClass().getInterfaces());
		e.setCallback(new WiseFrameInnerWebElementProxy(element));
		
		return (WebElement) e.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		List<String> currentPath = getCurrentFramePath();
		try {
			switchToFrame(this.framePath);
			return method.invoke(this.wrappedElement, args);
		} finally {
			switchToFrame(currentPath);
		}
	}
	
}
