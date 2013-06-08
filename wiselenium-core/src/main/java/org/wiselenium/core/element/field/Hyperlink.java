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

package org.wiselenium.core.element.field;

/**
 * Represents a HTML Hyperlink.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Hyperlink extends Field<Hyperlink> {
	
	/**
	 * Retrieves the specified URL of the page the link goes to.
	 * 
	 * @return The href of this link.
	 * @since 0.1.0
	 */
	String getHref();
	
	/**
	 * Retrieves the specification of where to open the linked document.
	 * 
	 * @return The target of this link.
	 * @since 0.1.0
	 */
	String getTarget();
	
	/**
	 * Retrieves the text of this hyperlink.
	 * 
	 * @return The text of this hyperlink.
	 * @since 0.1.0
	 */
	String getText();
	
}
