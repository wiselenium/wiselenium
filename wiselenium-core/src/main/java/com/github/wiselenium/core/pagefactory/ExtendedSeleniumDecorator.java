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
package com.github.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Allows the WisePageFactory to decorate fields or webElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
interface ExtendedSeleniumDecorator extends FieldDecorator {
	
	/**
	 * Decides how to decorate the list of webElements.
	 * 
	 * @param <E> The class type.
	 * @param clazz The class that will decorate each webElement of the list. If the class is not
	 * concrete, will lookup for its implementation matching the pattern
	 * classPackage.{impl}.className{Impl} yet. A more sofisticated search is planned to be
	 * implemented on the future.
	 * @param webElements The list of webElements that may be decorated.
	 * @return The list with the webElements decorated or empty if they shouldn't be decorated.
	 */
	<E> List<E> decorate(Class<E> clazz, List<WebElement> webElements);
	
	/**
	 * Decides how to decorate the webElement.
	 * 
	 * @param <E> The class type.
	 * @param clazz The class that will decorate the webElement. If the class is not concrete, will
	 * lookup for its implementation matching the pattern classPackage.{impl}.className{Impl} yet. A
	 * more sofisticated search is planned to be implemented on the future.
	 * @param webElement The webElement that may be decorated.
	 * @return The decorated webElement or null if it shouldn't be decorated.
	 */
	<E> E decorate(Class<E> clazz, WebElement webElement);
	
}
