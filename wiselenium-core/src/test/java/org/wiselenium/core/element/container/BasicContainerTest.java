package org.wiselenium.core.element.container;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class BasicContainerTest extends WiseTestNG<BasicContainerTest> {
	
	TablePage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.getDriver(), TablePage.class).and().get();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Table table = this.page.getTableWithCaptionAndHead();
		assertEquals(table, table.and());
	}
	
	@Test
	public void shouldFindElement() {
		Table table = this.page.getTableWithCaptionAndHead();
		TableBody tbody = table.findElement(TableBody.class, By.xpath("tbody"));
		assertNotNull(tbody);
	}
	
	@Test
	public void shouldFindElements() {
		TableBody tbody = this.page.getTableWithCaptionAndHead().getBody();
		List<TableRow> elements = tbody.findElements(TableRow.class, By.xpath("tr"));
		assertFalse(elements.isEmpty());
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getTableWithCaptionAndHead().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getTableWithCaptionAndHead().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getTableWithCaptionAndHead().getCssValue("border"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getTableWithCaptionAndHead().isDisplayed());
	}
	
	@Test
	public void shouldGetId() {
		assertFalse(this.page.getTableWithCaptionAndHead().getId().isEmpty());
	}
	
	@Test
	public void shouldGetStyleClass() {
		assertFalse(this.page.getTableWithCaptionAndHead().getStyleClass().isEmpty());
	}
	
	@Test
	public void shouldGetTitle() {
		assertFalse(this.page.getTableWithCaptionAndHead().getTitle().isEmpty());
	}
	
}
