package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.WisePageFactory.initElements;
import static org.wiselenium.core.element.field.TextPage.TITLE;
import static org.wiselenium.core.element.field.TextPage.URL;

import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class TextTest extends TestBase {
	
	@Test
	public void shouldClickButton() {
		this.driver.get(getAbsoluteFilePath(URL));
		TextPage page = initElements(this.driver, TextPage.class);
		assertEquals(page.getTitle(), TITLE);
		
		String keysToSend = "test";
		page.typeOnText(keysToSend);
		assertEquals(page.getTextValue(), keysToSend);
	}
	
	// TODO tests for its other methods
	
}
