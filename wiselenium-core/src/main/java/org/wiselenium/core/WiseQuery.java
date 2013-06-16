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
package org.wiselenium.core;

import java.util.List;

import org.openqa.selenium.By;

/**
 * Finds wise elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface WiseQuery {
	
	/**
	 * Finds the first element within the current context using the given mechanism.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element. Must be either WebElement or a type annotated with
	 * Field, Container or Frame. Will lookup for its implementation class following
	 * {@link org.wiselenium.core.util.ClasspathUtil#findImplementationClass(Class)} rules.
	 * @param by The locating mechanism to use.
	 * @return The element decorated or null if it shouldn't be decorated because the type didn't
	 * respect the clazz parameter specification.
	 * @since 0.1.0
	 */
	<E> E findElement(Class<E> clazz, By by);
	
	/**
	 * Finds all elements within the current context using the given mechanism.
	 * 
	 * @param <E> The type of the elements.
	 * @param clazz The class of the elements. Must be either WebElement or a type annotated with
	 * Field, Container or Frame. Will lookup for its implementation class following
	 * {@link org.wiselenium.core.util.ClasspathUtil#findImplementationClass(Class)} rules.
	 * @param by The locating mechanism to use.
	 * @return The elements decorated or an empty list if it shouldn't be decorated because the type
	 * didn't respect the clazz parameter specification.
	 * @since 0.1.0
	 */
	<E> List<E> findElements(Class<E> clazz, By by);
	
}
