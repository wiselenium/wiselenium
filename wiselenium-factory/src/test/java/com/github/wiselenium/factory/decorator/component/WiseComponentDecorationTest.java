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
package com.github.wiselenium.factory.decorator.component;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.TestResourceFinder;
import com.github.wiselenium.Wiselenium;
import com.github.wiselenium.element.DummyComponent;
import com.github.wiselenium.element.DummyFrame;

@SuppressWarnings("javadoc")
public class WiseComponentDecorationTest extends TestBase {
	
	private static final String DUMMY_PAGE = TestResourceFinder.getAbsolutePath("dummy.html");
	private static final String FRAMES_PAGE = TestResourceFinder.getAbsolutePath("frame.html");
	private static final By BY_SELECT1 = By.id("select1");
	
	@BeforeMethod
	public void initPage() {
		this.driver.get(DUMMY_PAGE);
	}
	
	@Test
	public void shouldRecognizeTypeToDecorate() {
		WiseComponentDecorator decorator = new WiseComponentDecorator(null);
		assertTrue(decorator.shouldDecorate(DummyComponent.class));
		assertFalse(decorator.shouldDecorate(DummyFrame.class));
		assertFalse(decorator.shouldDecorate(WebElement.class));
		assertFalse(decorator.shouldDecorate(Object.class));
	}
	
	@Test
	public void shouldDecorateComponent() {
		WebElement webElement = Wiselenium.findElement(WebElement.class, BY_SELECT1, this.driver);
		DummyComponent dummy = Wiselenium.decorateElement(DummyComponent.class, webElement);
		assertNotNull(dummy);
		assertNotNull(dummy.getRoot().toString());
		assertNotNull(dummy.getOptions());
		assertFalse(dummy.getOptions().isEmpty());
		for (WebElement option : dummy.getOptions()) {
			assertNotNull(option.toString());
		}
	}
	
	@Test
	public void shouldDecorateComponentEvenWhenNotFoundRightAway() {
		WebElement webElement = Wiselenium.findElement(WebElement.class, BY_SELECT1, this.driver);
		this.driver.get(FRAMES_PAGE);
		DummyComponent dummy = Wiselenium.decorateElement(DummyComponent.class, webElement);
		assertNotNull(dummy);
	}
	
	@Test
	public void shouldDecorateComponents() {
		List<WebElement> webElements = Wiselenium.findElements(WebElement.class, BY_SELECT1, this.driver);
		List<DummyComponent> dummies = Wiselenium.decorateElements(DummyComponent.class, webElements);
		assertNotNull(dummies);
		assertFalse(dummies.isEmpty());
	}
	
	@Test
	public void shouldDecorateComponentsEvenWhenNotFoundRightAway() {
		List<WebElement> webElements = Wiselenium.findElements(WebElement.class, BY_SELECT1, this.driver);
		this.driver.get(FRAMES_PAGE);
		List<DummyComponent> dummies = Wiselenium.decorateElements(DummyComponent.class, webElements);
		assertNotNull(dummies);
	}
	
}