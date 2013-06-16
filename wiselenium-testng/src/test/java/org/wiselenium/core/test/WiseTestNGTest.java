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
package org.wiselenium.core.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.test.annotation.Page;

@SuppressWarnings("javadoc")
public class WiseTestNGTest extends WiseTestNG<WiseTestNGTest> {
	
	@Page
	private DummyPage page;
	
	
	@Override
	public String getUrl() {
		return DummyPage.URL;
	}
	
	@Test
	public void shouldFindElement() {
		Text text = this.get(DummyPage.URL).and().findElement(Text.class, By.id("text"));
		assertNotNull(text);
	}
	
	@Test
	public void shouldFindElements() {
		List<Radiobutton> radiobuttons = this.get(DummyPage.URL).and()
			.findElements(Radiobutton.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertFalse(radiobuttons.isEmpty());
	}
	
	@BeforeClass
	public void shouldHavePageInjected() {
		assertNotNull(this.page);
	}
	
	@Test
	public void shouldInitElementsWithClass() {
		DummyPage page = this.initElements(DummyPage.class);
		assertNotNull(page);
	}
	
	@Test
	public void shouldInitElementsWithInstance() {
		DummyPage page = new DummyPage();
		this.initElements(page);
		assertNotNull(page.getText());
	}
	
	@BeforeClass
	public void shouldStartTestAtUrl() {
		assertEquals(this.getDriver().getCurrentUrl(), this.getUrl());
	}
	
	@Test
	public void shouldTakeScreenShot() {
		String fileName = "wiseTestScreenShot.png";
		this.takeScreenShot(fileName);
		File file = new File(this.getScreenShotPath() + fileName);
		file.deleteOnExit();
		assertTrue(file.exists());
	}
	
	@Test
	public void shouldWaitFor() {
		WebDriverWait webDriverWait1 = this.waitFor(5);
		assertNotNull(webDriverWait1);
		
		WebDriverWait webDriverWait2 = this.waitFor(5, 1);
		assertNotNull(webDriverWait2);
	}
	
}
