package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.field.TextPage.HIDDEN_TEXT_VALUE;
import static org.wiselenium.core.element.field.TextPage.TEXT_CLICKED_MESSAGE;
import static org.wiselenium.core.element.field.TextPage.TEXT_MAXLENGTH;
import static org.wiselenium.core.element.field.TextPage.TITLE;
import static org.wiselenium.core.element.field.TextPage.URL;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class TextTest extends TestBase {
	
	TextPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, TextPage.class);
		this.page.get(getAbsoluteFilePath(URL));
		assertEquals(this.page.getTitle(), TITLE);
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Text text = this.page.getText();
		assertEquals(text, text.and());
	}
	
	@Test
	public void shouldClear() {
		String keysToSend = "test";
		assertTrue(this.page.getText().sendKeys(keysToSend).and().clear().and().getValue()
			.isEmpty());
	}
	
	@Test
	public void shouldClick() {
		this.page.getText().click();
		assertEquals(this.page.getMessage(), TEXT_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getText().getAttribute("id"));
		assertNotNull(this.page.getHiddenText().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getText().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getHiddenText().getCssValue("visibility"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getText().isDisplayed());
		assertFalse(this.page.getHiddenText().isDisplayed());
	}
	
	@Test
	public void shouldGetMaxlength() {
		assertEquals(this.page.getText().getMaxLength(), TEXT_MAXLENGTH);
		assertNull(this.page.getReadonlyText().getMaxLength());
	}
	
	@Test
	public void shouldGetReadOnly() {
		assertFalse(this.page.getText().isReadOnly());
		assertFalse(this.page.getHiddenText().isReadOnly());
		
		assertTrue(this.page.getReadonlyText().isReadOnly());
		assertTrue(this.page.getReadonlyReadonlyText().isReadOnly());
		assertTrue(this.page.getReadonlyTrueText().isReadOnly());
		// readonly="false" is still rendered as readonly by the browsers
		assertTrue(this.page.getReadonlyFalseText().isReadOnly());
	}
	
	@Test
	public void shouldGetValue() {
		assertTrue(this.page.getText().getValue().isEmpty());
		assertEquals(this.page.getHiddenText().getValue(), HIDDEN_TEXT_VALUE);
	}
	
	@Test
	public void shouldSendKeys() {
		String keysToSend = "test";
		assertEquals(this.page.getText().sendKeys(keysToSend).and().getValue(), keysToSend);
	}
	
}
