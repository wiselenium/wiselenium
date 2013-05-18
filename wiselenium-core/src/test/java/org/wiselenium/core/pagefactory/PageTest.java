package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.FileUtils;
import org.wiselenium.core.TestBase;
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
	public void shouldFindElements() {
		// TODO findElements test
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
	public void shouldReturnCorrectElementOnFindElement() {
		// if the class is generic typed, the cast is unnecessary
		Text text = (Text) this.page.findElement(Text.class, By.id("text"));
		assertNotNull(text);
	}
	
	@Test
	public void shouldReturnNullOnFindElement() {
		assertNull(this.page.findElement(Object.class, By.id("text")));
	}
	
	@Test
	public void shouldReturnWebElementOnFindElement() {
		assertNotNull(this.page.findElement(WebElement.class, By.id("text")));
	}
}
