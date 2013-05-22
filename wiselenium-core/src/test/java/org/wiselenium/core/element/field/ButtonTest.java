package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.field.ButtonPage.BUTTON_CLICKED_MESSAGE;
import static org.wiselenium.core.element.field.ButtonPage.BUTTON_VALUE;
import static org.wiselenium.core.element.field.ButtonPage.HIDDEN_BUTTON_VALUE;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class ButtonTest extends TestBase {
	
	ButtonPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, ButtonPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldClick() {
		this.page.getButton().click();
		assertEquals(this.page.getMessage(), BUTTON_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getButton().isEnabled());
		assertFalse(this.page.getDisabledButton().isEnabled());
	}
	
	@Test
	public void shouldGetType() {
		assertEquals(this.page.getButton().getType(), "button");
		assertEquals(this.page.getResetButton().getType(), "reset");
		assertEquals(this.page.getSubmitButton().getType(), "submit");
	}
	
	@Test
	public void shouldGetValue() {
		assertEquals(this.page.getButton().getValue(), BUTTON_VALUE);
		assertEquals(this.page.getHiddenButton().getValue(), HIDDEN_BUTTON_VALUE);
	}
	
}
