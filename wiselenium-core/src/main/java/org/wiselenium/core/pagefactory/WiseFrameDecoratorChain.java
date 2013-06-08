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

package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.util.AnnotationUtil.isAnnotationPresent;
import static org.wiselenium.core.util.ClasspathUtil.findImplementationClass;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements into Containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
class WiseFrameDecoratorChain extends ExtendedSeleniumDecoratorChainTemplate {
	
	WiseFrameDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		Class<? extends E> implementationClass = findImplementationClass(clazz);
		return WiseFrameProxy.getInstance(implementationClass, webElement);
	}
	
	@Override
	protected <E> List<E> decorateWebElements(Class<E> clazz, List<WebElement> webElements) {
		return WiseElementListProxy.getInstance(clazz, webElements, this.factory);
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		return isAnnotationPresent(clazz, org.wiselenium.core.annotation.Frame.class);
	}
	
}
