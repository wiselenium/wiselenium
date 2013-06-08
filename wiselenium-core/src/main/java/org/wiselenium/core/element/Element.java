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

package org.wiselenium.core.element;

/**
 * Represents a HTML Element.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The element type.
 * @since 0.1.0
 */
public interface Element<T> {
	
	/**
	 * Returns this element object in order to allow chain calls in a more fluent way.
	 * 
	 * @return This element object.
	 * @since 0.1.0
	 */
	T and();
	
	/**
	 * Gets the value of a given attribute of the element.
	 * 
	 * @param name The name of the attribute.
	 * @return The attribute's current value or null if the value is not set.
	 * @since 0.1.0
	 */
	String getAttribute(String name);
	
	/**
	 * Gets the value of a given CSS property.
	 * 
	 * @param propertyName The name of the property.
	 * @return The current, computed value of the property. If it doesn't have the property, an
	 * empty string is returned.
	 * @since 0.1.0
	 */
	String getCssValue(String propertyName);
	
	/**
	 * Retrieves the id of this element.
	 * 
	 * @return The id of this element.
	 * @since 0.1.0
	 */
	String getId();
	
	/**
	 * Retrieves the style class of this element.
	 * 
	 * @return The style class of this element.
	 * @since 0.1.0
	 */
	String getStyleClass();
	
	/**
	 * Retrieves the title of this element.
	 * 
	 * @return The title of this element.
	 * @since 0.1.0
	 */
	String getTitle();
	
	/**
	 * Verifies if the element is displayed on the page.
	 * 
	 * @return Whether or not the element is displayed.
	 * @since 0.1.0
	 */
	boolean isDisplayed();
	
}
