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
package com.github.wiselenium.testng;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.wiselenium.elements.field.Radiobutton;
import com.github.wiselenium.elements.field.Text;
import com.github.wiselenium.testng.WiseTest;
import com.github.wiselenium.testng.annotation.Page;
import com.github.wiselenium.testng.page.DummyPage;

@SuppressWarnings("javadoc")
public class WiseTestTest extends WiseTest {
	
	@Page
	private DummyPage page;
	
	
	@BeforeClass
	public void goToPageInjected() {
		assertNotNull(this.page);
		this.page.get();
	}
	
	
	@Test
	public void shouldFindElement() {
		Text text = this.findElement(Text.class, By.id("text"));
		assertNotNull(text);
	}
	
	@Test
	public void shouldFindElements() {
		List<Radiobutton> radiobuttons = this.findElements(Radiobutton.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertFalse(radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldInitElements() {
		DummyPage page = this.initElements(DummyPage.class);
		assertNotNull(page);
	}
	
	@Test
	public void shouldInitElementsOfInstance() {
		DummyPage page = new DummyPage();
		this.initElements(page);
		assertNotNull(page.getText());
	}
	
	@Test
	public void shouldTakeScreenShot() {
		String fileName = "target/screenshots/wiseTestScreenShot.png";
		this.takeScreenShot(fileName);
		File file = new File(fileName);
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
