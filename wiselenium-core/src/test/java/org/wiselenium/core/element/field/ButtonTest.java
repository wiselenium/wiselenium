package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.WisePageFactory.initElements;
import static org.wiselenium.core.element.field.ButtonPage.SUCCESS_MESSAGE;
import static org.wiselenium.core.element.field.ButtonPage.URL;

import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class ButtonTest extends TestBase {
	
	@Test
	public void shouldClickButton() {
		this.driver.get(getAbsoluteFilePath(URL));
		ButtonPage buttonPage = initElements(this.driver, ButtonPage.class);
		buttonPage.clickButton();
		assertEquals(buttonPage.getMessage(), SUCCESS_MESSAGE);
	}
	
	@Test
	public void shouldClickButtonInPageWithWebDriverConstructor() {
		this.driver.get(getAbsoluteFilePath(ButtonPageWithWebDriverConstructor.URL));
		ButtonPageWithWebDriverConstructor buttonPage = initElements(this.driver,
			ButtonPageWithWebDriverConstructor.class);
		buttonPage.clickButton();
		assertEquals(buttonPage.getMessage(), ButtonPageWithWebDriverConstructor.SUCCESS_MESSAGE);
	}
	
}
