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
 * */

package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.WiseElementProxyUtil.isGetWrappedElement;
import static org.wiselenium.core.util.FrameUtil.getCurrentFramePath;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.util.FrameUtil;

/**
 * The wiselenium proxy for frames.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	private List<String> parentFramePath;
	private boolean elementsInitialized;
	
	
	private WiseFrameProxy(WebElement webElement) {
		this.wrappedElement = webElement;
	}
	
	@SuppressWarnings("unchecked")
	static <E> E getInstance(Class<E> implementationClass, WebElement webElement) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implementationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(new WiseFrameProxy(webElement));
		
		try {
			return (E) e.create();
		} catch (IllegalArgumentException ex) {
			throw new ClassWithoutNoArgConstructorException(implementationClass, ex);
		}
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return this.wrappedElement;
		
		List<String> currentPath = getCurrentFramePath();
		try {
			this.switchToFrame();
			this.initElements(obj);
			return proxy.invokeSuper(obj, args);
		} finally {
			FrameUtil.switchToFrame(currentPath);
		}
	}
	
	private synchronized List<String> getParentFramePath() {
		if (this.parentFramePath == null) this.parentFramePath = FrameUtil.getCurrentFramePath();
		return this.parentFramePath;
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(WiseThreadLocal.getDriver(), obj);
			this.elementsInitialized = true;
		}
	}
	
	private synchronized void switchToFrame() {
		FrameUtil.switchToFrame(this.getParentFramePath());
		WiseThreadLocal.getDriver().switchTo().frame(this.wrappedElement);
	}
	
}
