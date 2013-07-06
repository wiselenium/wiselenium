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
package com.github.wiselenium.core.element.field.impl;

import static com.github.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.github.wiselenium.core.WiseContext;
import com.github.wiselenium.core.WiseException;
import com.github.wiselenium.core.element.BasicElement;
import com.github.wiselenium.core.element.field.Field;

/**
 * Basic implementation of a common Field. <br/>
 * Should be extended to reflect your own field methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The field type.
 * @since 0.1.0
 */
public class BasicField<T extends Field<T>> extends BasicElement<T> implements Field<T> {
	
	@SuppressWarnings("unchecked")
	@Override
	public T click() {
		unwrapWebElement(this).click();
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T doubleClick() {
		WebDriver driver = WiseContext.getDriver();
		if (driver == null)
			throw new WiseException(
				"The driver must be set on the WiseContext as a pre-condition for the doubleClick() method");
		Action action = new Actions(driver).doubleClick(unwrapWebElement(this)).build();
		action.perform();
		return (T) this;
	}
	
}
