package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.WisePageFactory.initElements;
import static org.wiselenium.core.element.field.ButtonPage.BUTTON_CLICKED_MESSAGE;
import static org.wiselenium.core.element.field.ButtonPage.BUTTON_VALUE;
import static org.wiselenium.core.element.field.ButtonPage.HIDDEN_BUTTON_VALUE;
import static org.wiselenium.core.element.field.ButtonPage.TITLE;
import static org.wiselenium.core.element.field.ButtonPage.URL;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class ButtonTest extends TestBase {
	
	ButtonPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, ButtonPage.class);
		this.page.get(getAbsoluteFilePath(URL));
		assertEquals(this.page.getTitle(), TITLE);
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Button button = this.page.getButton();
		assertEquals(button, button.and());
	}
	
	@Test
	public void shouldClick() {
		this.page.getButton().click();
		assertEquals(this.page.getMessage(), BUTTON_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getButton().getAttribute("id"));
		assertNotNull(this.page.getHiddenButton().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getButton().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getHiddenButton().getCssValue("visibility"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getButton().isDisplayed());
		assertFalse(this.page.getHiddenButton().isDisplayed());
	}
	
	@Test
	public void shouldGetValue() {
		assertEquals(this.page.getButton().getValue(), BUTTON_VALUE);
		assertEquals(this.page.getHiddenButton().getValue(), HIDDEN_BUTTON_VALUE);
	}
	
}
