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
package com.github.wiselenium.elements.field;

import com.github.wiselenium.elements.Element;

/**
 * Represents an abstraction of a HTML Field.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The field type.
 * @since 0.1.0
 */
@com.github.wiselenium.factory.annotation.Field
public interface Field<T extends Field<T>> extends Element<T> {
	
	// TODO remove field and use only the container logic, but named as component
	
	/**
	 * Clicks on this field.
	 * 
	 * @return This field object in order to allow chain calls.
	 * @since 0.1.0
	 */
	T click();
	
	/**
	 * Double clicks on this field.
	 * 
	 * @return This field object in order to allow chain calls.
	 * @since 0.1.0
	 */
	T doubleClick();
	
}
