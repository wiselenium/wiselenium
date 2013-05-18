package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.FileUtils;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.element.field.Text;

@SuppressWarnings({ "javadoc", "rawtypes", "unchecked" })
public class PageTest extends TestBase {
	
	private Page page;
	
	private final String googleUrl = "http://www.google.com";
	private final String DUMMY_PAGE_URL = "dummy.html";
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, Page.class);
		this.page.get(FileUtils.getAbsoluteFilePath(this.DUMMY_PAGE_URL));
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
		assertTrue(this.page.get(this.googleUrl).and().getCurrentUrl().contains("google"));
	}
	
	@Test
	public void shouldGetPageSource() {
		assertNotNull(this.page.get(this.googleUrl).getPageSource());
	}
	
	@Test
	public void shouldGetTitle() {
		String title = this.page.getTitle();
		String newTitle = this.page.get(this.googleUrl).and().getTitle();
		assertNotEquals(newTitle, title);
	}
	
	@Test
	public void shouldGetWrappedDriver() {
		assertNotNull(this.page.getWrappedDriver());
	}
	
	@Test
	public void shouldReturnCorrectContainerListOnFindElements() {
		List<Select> selects = this.page.findElements(Select.class, By.name("select"));
		assertNotNull(selects);
		assertFalse(selects.isEmpty());
	}
	
	@Test
	public void shouldReturnCorrectFieldListOnFindElements() {
		List<Radiobutton> radiobuttons = this.page.findElements(Radiobutton.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertFalse(radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldReturnCorrectFieldOnFindElement() {
		// if the class is generic typed, the cast is unnecessary
		Text text = (Text) this.page.findElement(Text.class, By.id("text"));
		assertNotNull(text);
	}
	
	@Test
	public void shouldReturnEmptyContainerListOnFindElements() {
		List selects = this.page.findElements(Object.class, By.name("select"));
		assertNotNull(selects);
		assertTrue(selects.isEmpty());
	}
	
	@Test
	public void shouldReturnEmptyFieldListOnFindElements() {
		List radiobuttons = this.page.findElements(Object.class, By.name("sex"));
		assertNotNull(radiobuttons);
		assertTrue(radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldReturnNullFieldOnFindElement() {
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
	
}
