package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class LabelTest extends WiseTestNG<LabelTest> {
	
	LabelPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(LabelPage.class);
		this.page.get();
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
