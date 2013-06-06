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
