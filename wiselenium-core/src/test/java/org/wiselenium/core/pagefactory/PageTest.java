package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.ButtonPage;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.element.field.Text;

@SuppressWarnings({ "javadoc", "rawtypes", "unchecked" })
public class PageTest extends TestBase {
	
	private Page page;
	
	private static final String DUMMY_PAGE_URL = getAbsoluteFilePath("dummy.html");
	private static final String BUTTON_PAGE_URL = getAbsoluteFilePath(ButtonPage.URL);
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, Page.class);
		this.page.get(DUMMY_PAGE_URL);
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
		assertTrue(this.page.get(BUTTON_PAGE_URL).and().getCurrentUrl().contains("button"));
	}
	
	@Test
	public void shouldGetPageSource() {
		assertNotNull(this.page.get(BUTTON_PAGE_URL).getPageSource());
	}
	
	@Test
	public void shouldGetTitle() {
		String title = this.page.getTitle();
		String newTitle = this.page.get(BUTTON_PAGE_URL).and().getTitle();
		assertNotEquals(newTitle, title);
	}
	
	@Test
	public void shouldGetWrappedDriver() {
		assertNotNull(this.page.getWrappedDriver());
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
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldThrowExceptionWhenElementIsNotFound() {
		this.page.findElement(WebElement.class, By.id("inexistent"));
	}
	
}
