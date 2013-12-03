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
package com.github.wiselenium.factory.exception;

import java.lang.reflect.Field;

/**
 * Represents an exception thrown due to an error while initializing the elements of an object.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class ElementInitializationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param field
	 * @param clazz The class of the page.
	 * @param t The cause of the exception.
	 */
	public ElementInitializationException(Field field, Class<?> clazz, Throwable t) {
		super(String.format("Failure while trying to initialize the element %s of the object %s",
				field.getName(), clazz.getName()),
				t);
	}
	
}
