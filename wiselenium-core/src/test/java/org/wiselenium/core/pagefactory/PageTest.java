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
package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.ButtonPage;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings({ "javadoc", "rawtypes", "unchecked" })
public class PageTest extends WiseTestNG<PageTest> {
	
	private Page page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(Page.class);
		this.page.get(DummyPage.URL);
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		assertNotNull(this.page.and());
	}
	
	@Test
	public void shouldExecuteScript() {
		assertNotNull(this.page.executeScript("return this.name;"));
	}
	
	@Test
	public void shouldGetCurrentUrl() {
		assertTrue(this.page.get(ButtonPage.URL).and().getCurrentUrl().contains("button"));
	}
	
	@Test
	public void shouldGetPageSource() {
		assertNotNull(this.page.get(ButtonPage.URL).getPageSource());
	}
	
	@Test
	public void shouldGetTitle() {
		String title = this.page.getTitle();
		String newTitle = this.page.get(ButtonPage.URL).and().getTitle();
		assertNotEquals(newTitle, title);
	}
	
	@Test
	public void shouldGetWrappedDriver() {
		assertNotNull(this.page.getWrappedDriver());
	}
	
	@Test
	public void shouldInitNextPage() {
		this.initElements(DummyPage.class).and().getLink().and().click();
		// wouldn't need any cast if the page used generics
		ButtonPage buttonPage = (ButtonPage) this.page.initNextPage(ButtonPage.class);
		assertNotNull(buttonPage);
	}
	
	@Test
	public void shouldReturnContainerListOnFindElements() {
		List<Select> selects = this.page.findElements(Select.class, By.name("select"));
		assertNotNull(selects);
		assertFalse(selects.isEmpty());
	}
	
	@Test
	public void shouldReturnEmptyContainerListOnElementsNotFound() {
		List selects = this.page.findElements(Object.class, By.name("select"));
		assertNotNull(selects);
		assertTrue(selects.isEmpty());
	}
	
	@Test
	public void shouldReturnEmptyFieldListOnElementsNotFound() {
		List radiobuttons = this.page.findElements(Object.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertTrue(radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldReturnFieldListOnFindElements() {
		List<Radiobutton> radiobuttons = this.page.findElements(Radiobutton.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertFalse(radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldReturnFieldOnFindElement() {
		// if the class is generic typed, the cast is unnecessary
		Text text = (Text) this.page.findElement(Text.class, By.id("text"));
		assertNotNull(text);
	}
	
	@Test
	public void shouldReturnNullFieldOnFindElementWithAnInvalidType() {
		assertNull(this.page.findElement(Object.class, By.id("text")));
	}
	
	@Test
	public void shouldReturnWebElementListOnFindElements() {
		List<WebElement> radiobuttons = this.page.findElements(WebElement.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertFalse(radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldReturnWebElementOnFindElement() {
		assertNotNull(this.page.findElement(WebElement.class, By.id("text")));
	}
	
	@Test
	public void shouldTakeScreenShot() {
		String fileName = "pageScreenShot.png";
		this.page.takeScreenShot(fileName);
		File file = new File(fileName);
		file.deleteOnExit();
		assertTrue(file.exists());
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldThrowExceptionWhenElementIsNotFound() {
		this.page.findElement(WebElement.class, By.id("inexistent"));
	}
	
	@Test
	public void shouldWaitFor() {
		WebDriverWait webDriverWait1 = this.page.waitFor(5);
		assertNotNull(webDriverWait1);
		
		WebDriverWait webDriverWait2 = this.page.waitFor(5, 1);
		assertNotNull(webDriverWait2);
	}
	
}
