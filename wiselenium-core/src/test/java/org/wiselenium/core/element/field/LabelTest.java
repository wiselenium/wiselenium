package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.field.LabelPage.LABEL_CLICKED_MESSAGE;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class LabelTest extends TestBase {
	
	LabelPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, LabelPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Label label = this.page.getLabel();
		assertEquals(label, label.and());
	}
	
	@Test
	public void shouldClick() {
		this.page.getLabel().click();
		assertEquals(this.page.getMessage(), LABEL_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getLabel().getAttribute("id"));
		assertNotNull(this.page.getHiddenLabel().getAttribute("id"));
		assertFalse(this.page.getLabel().getAttribute("id").isEmpty());
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getLabel().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getHiddenLabel().getCssValue("visibility"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getLabel().isDisplayed());
		assertFalse(this.page.getHiddenLabel().isDisplayed());
	}
	
	@Test
	public void shouldGetFor() {
		assertEquals(this.page.getLabel().getFor(), LabelPage.LABEL_FOR);
	}
	
	@Test
	public void shouldGetText() {
		assertEquals(this.page.getLabel().getText(), LabelPage.LABEL_TEXT);
	}
	
}
