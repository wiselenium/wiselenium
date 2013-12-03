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
package com.github.wiselenium.elements.frame.impl;

import java.util.List;

import org.openqa.selenium.By;

import com.github.wiselenium.WiseContext;
import com.github.wiselenium.Wiselenium;
import com.github.wiselenium.elements.BasicElement;
import com.github.wiselenium.elements.frame.Frame;

/**
 * Basic implementation of a common Frame. <br/>
 * Should be extended to reflect your own frame methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The frame type.
 * @since 0.1.0
 */
public class FrameImpl<T extends Frame<T>> extends BasicElement<T> implements Frame<T> {
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		return Wiselenium.findElement(clazz, by, WiseContext.getDriver());
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		return Wiselenium.findElements(clazz, by, WiseContext.getDriver());
	}
	
}
