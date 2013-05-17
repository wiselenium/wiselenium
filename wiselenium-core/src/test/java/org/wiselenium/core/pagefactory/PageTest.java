package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class PageTest extends TestBase {
	
	@SuppressWarnings("rawtypes")
	private Page page;
	
	private final String googleUrl = "http://www.google.com";
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, Page.class);
		this.page.get(getAbsoluteFilePath(this.DUMMY_PAGE_URL));
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
	public void shouldFindElement() {
		// TODO findElement test
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
	
}
