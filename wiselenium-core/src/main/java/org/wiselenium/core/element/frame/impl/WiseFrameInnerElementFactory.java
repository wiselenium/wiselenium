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

import static org.wiselenium.core.util.AnnotationUtil.isAnnotationPresent;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.annotation.Container;
import org.wiselenium.core.annotation.Field;

/**
 * Factory of frame inner elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameInnerElementFactory {
	
	private WiseFrameInnerElementFactory() {}
	
	/**
	 * Returns a proxy of the element, so that it can be used transparently even outside the frame
	 * class.
	 * 
	 * @param <E> The element type.
	 * @param element The element.
	 * @return The element proxied.
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> E getInstance(E element) {
		if (isAnnotationPresent(element.getClass(), Field.class))
			return WiseFrameInnerFieldProxy.getInstance(element);
		
		if (isAnnotationPresent(element.getClass(), Container.class))
			return WiseFrameInnerContainerProxy.getInstance(element);
		
		if (isAnnotationPresent(element.getClass(), org.wiselenium.core.annotation.Frame.class))
			return WiseFrameInnerFrameProxy.getInstance(element);
		
		if (WebElement.class.isAssignableFrom(element.getClass())) {
			WebElement webElement = (WebElement) element;
			return (E) WiseFrameInnerWebElementProxy.getInstance(webElement);
		}
		
		throw new ElementTypeNotSupportedException(element.getClass());
	}
	
}
