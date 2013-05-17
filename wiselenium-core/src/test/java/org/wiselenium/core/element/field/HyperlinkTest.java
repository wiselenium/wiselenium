package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.field.HyperlinkPage.HYPERLINK_CLICKED_MESSAGE;
import static org.wiselenium.core.element.field.HyperlinkPage.HYPERLINK_HREF;
import static org.wiselenium.core.element.field.HyperlinkPage.HYPERLINK_TARGET;
import static org.wiselenium.core.element.field.HyperlinkPage.HYPERLINK_TEXT;
import static org.wiselenium.core.element.field.HyperlinkPage.URL;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class HyperlinkTest extends TestBase {
	
	HyperlinkPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, HyperlinkPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Hyperlink hyperlink = this.page.getHyperlink();
		assertEquals(hyperlink, hyperlink.and());
	}
	
	@Test
	public void shouldClick() {
		this.page.getHyperlink().click();
		assertEquals(this.page.getMessage(), HYPERLINK_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getHyperlink().getAttribute("id"));
		assertNotNull(this.page.getHiddenHyperlink().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getHyperlink().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getHiddenHyperlink().getCssValue("visibility"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getHyperlink().isDisplayed());
		assertFalse(this.page.getHiddenHyperlink().isDisplayed());
	}
	
	@Test
	public void shouldGetHref() {
		assertTrue(this.page.getHyperlink().getHref().endsWith(URL + HYPERLINK_HREF));
	}
	
	@Test
	public void shouldGetTarget() {
		assertEquals(this.page.getHyperlink().getTarget(), HYPERLINK_TARGET);
	}
	
	@Test
	public void shouldGetText() {
		assertEquals(this.page.getHyperlink().getText(), HYPERLINK_TEXT);
	}
	
}
