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
package com.github.wiselenium.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import com.github.wiselenium.factory.annotation.Root;

/**
 * Basic implementation of a common Element. <br/>
 * Should be extended to reflect your own element methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The element type.
 * @since 0.1.0
 */
public class BasicElement<T extends Element<T>> implements Element<T>, WrapsElement {
	
	@Root
	private WebElement root;
	
	@SuppressWarnings("unchecked")
	@Override
	public T and() {
		return (T) this;
	}
	
	@Override
	public String getAttribute(String name) {
		return this.root.getAttribute(name);
	}
	
	@Override
	public String getCssValue(String propertyName) {
		return this.root.getCssValue(propertyName);
	}
	
	@Override
	public String getId() {
		return this.getAttribute("id");
	}
	
	@Override
	public String getStyleClass() {
		return this.getAttribute("class");
	}
	
	@Override
	public String getTitle() {
		return this.getAttribute("title");
	}
	
	@Override
	public boolean isDisplayed() {
		return this.root.isDisplayed();
	}
	
	@Override
	public WebElement getWrappedElement() {
		return this.root;
	}
	
}
