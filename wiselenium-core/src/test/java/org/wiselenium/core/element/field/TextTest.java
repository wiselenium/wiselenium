package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.field.TextPage.HIDDEN_TEXT_VALUE;
import static org.wiselenium.core.element.field.TextPage.TEXT_CLICKED_MESSAGE;
import static org.wiselenium.core.element.field.TextPage.TEXT_MAXLENGTH;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class TextTest extends WiseTestNG<TextTest> {
	
	TextPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.getDriver(), TextPage.class);
		this.page.get();
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
	public void shouldGetEnabled() {
		assertTrue(this.page.getText().isEnabled());
		assertFalse(this.page.getDisabledText().isEnabled());
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
