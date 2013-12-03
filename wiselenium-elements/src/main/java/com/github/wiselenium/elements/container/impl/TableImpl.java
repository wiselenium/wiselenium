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
package com.github.wiselenium.elements.container.impl;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.elements.container.Table;
import com.github.wiselenium.elements.container.TableBody;
import com.github.wiselenium.elements.container.TableFoot;
import com.github.wiselenium.elements.container.TableHead;

/**
 * Implementation of a HTML Table.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class TableImpl extends ContainerImpl<Table> implements Table {
	
	@FindBy(tagName = "caption")
	private WebElement caption;
	
	@FindBy(tagName = "tbody")
	private TableBody body;
	
	@FindBy(tagName = "tfoot")
	private TableFoot foot;
	
	@FindBy(tagName = "thead")
	private TableHead head;
	
	
	@Override
	public TableBody getBody() {
		return this.body;
	}
	
	@Override
	public String getCaption() {
		try {
			return this.caption.getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Override
	public TableFoot getFoot() {
		try {
			if (this.foot.isDisplayed()) {
				return this.foot;
			}
			return null;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Override
	public TableHead getHead() {
		try {
			if (this.head.isDisplayed()) {
				return this.head;
			}
			return null;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
}
