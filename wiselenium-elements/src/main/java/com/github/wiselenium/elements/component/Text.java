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
package com.github.wiselenium.elements.component;

/**
 * Represents a HTML input text;
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public interface Text extends Component<Text> {
	
	/**
	 * Clears this text content.
	 * 
	 * @return This text object.
	 * @since 0.3.0
	 */
	Text clear();
	
	/**
	 * Retrieves the the maximum number of characters allowed in this text component.
	 * 
	 * @return The maximum number of characters allowed.
	 * @since 0.3.0
	 */
	Integer getMaxLength();
	
	/**
	 * Retrieves the value of this text.
	 * 
	 * @return The innerText of this element.
	 * @since 0.3.0
	 */
	String getValue();
	
	/**
	 * Retrieves whether this test is enabled or not.
	 * 
	 * @return If this text is enabled.
	 * @since 0.3.0
	 */
	boolean isEnabled();
	
	/**
	 * Retrieves whether this test is read-only or not.
	 * 
	 * @return If this text is read-only.
	 * @since 0.3.0
	 */
	boolean isReadOnly();
	
	/**
	 * Types into this text.
	 * 
	 * @param keysToSend The content to be typed.
	 * @return This text object.
	 * @since 0.3.0
	 */
	Text sendKeys(CharSequence... keysToSend);
	
}
