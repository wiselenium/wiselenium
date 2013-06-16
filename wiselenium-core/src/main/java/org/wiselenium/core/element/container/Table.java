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
package org.wiselenium.core.element.container;

/**
 * Represents a HTML Table.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Table extends Container<Table> {
	
	/**
	 * Returns the body of the table (tbody).
	 * 
	 * @return The body of the table.
	 * @since 0.1.0
	 */
	TableBody getBody();
	
	/**
	 * Returns the caption of the table.
	 * 
	 * @return The caption of the table.
	 * @since 0.1.0
	 */
	String getCaption();
	
	/**
	 * Returns the foot of the table (tfoot).
	 * 
	 * @return The foot of the table.
	 * @since 0.1.0
	 */
	TableFoot getFoot();
	
	/**
	 * Returns the head of the table (thead).
	 * 
	 * @return The head of the table (thead).
	 * @since 0.1.0
	 */
	TableHead getHead();
	
}
