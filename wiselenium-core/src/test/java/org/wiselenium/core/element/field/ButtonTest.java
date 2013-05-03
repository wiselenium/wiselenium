package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.WisePageFactory.initElements;
import static org.wiselenium.core.element.field.ButtonPage.SUCCESS_MESSAGE;
import static org.wiselenium.core.element.field.ButtonPage.TITLE;
import static org.wiselenium.core.element.field.ButtonPage.URL;

import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class ButtonTest extends TestBase {
	
	@Test
	public void shouldClickButton() {
		this.driver.get(getAbsoluteFilePath(URL));
		ButtonPage page = initElements(this.driver, ButtonPage.class);
		assertEquals(page.getTitle(), TITLE);
		page.clickButton();
		assertEquals(page.getMessage(), SUCCESS_MESSAGE);
	}
	
	// TODO erase test? there's already a test for injections...
	@Test
	public void shouldClickButtonInPageWithWebDriverConstructor() {
		this.driver.get(getAbsoluteFilePath(ButtonPageWithWebDriverConstructor.URL));
		ButtonPageWithWebDriverConstructor page = initElements(this.driver,
			ButtonPageWithWebDriverConstructor.class);
		assertEquals(page.getTitle(), ButtonPageWithWebDriverConstructor.TITLE);
		page.clickButton();
		assertEquals(page.getMessage(), ButtonPageWithWebDriverConstructor.SUCCESS_MESSAGE);
	}
	
	// TODO tests for its other methods
	
}
