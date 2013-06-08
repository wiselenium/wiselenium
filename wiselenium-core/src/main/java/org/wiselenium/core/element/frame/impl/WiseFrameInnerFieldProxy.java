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

package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.getWrappedElement;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.isGetWrappedElement;
import static org.wiselenium.core.util.FrameUtil.getCurrentFramePath;
import static org.wiselenium.core.util.FrameUtil.switchToFrame;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.wiselenium.core.util.FrameUtil;

/**
 * The wiselenium proxy for frame inner fields.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameInnerFieldProxy<E> implements MethodInterceptor {
	
	private final E wrappedElement;
	private final List<String> framePath;
	
	
	private WiseFrameInnerFieldProxy(E element) {
		this.wrappedElement = element;
		this.framePath = FrameUtil.getCurrentFramePath();
	}
	
	static <E> E getInstance(E element) {
		return WiseFrameInnerElementUtil.createProxy(element, new WiseFrameInnerFieldProxy<E>(
			element));
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return getWrappedElement(this.wrappedElement);
		
		List<String> currentPath = getCurrentFramePath();
		try {
			switchToFrame(this.framePath);
			return proxy.invokeSuper(obj, args);
		} finally {
			switchToFrame(currentPath);
		}
	}
	
}
