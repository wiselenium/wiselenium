package org.wiselenium.core.annotation;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;
import org.wiselenium.core.test.annotation.Page;

@SuppressWarnings("javadoc")
public class AjaxElementTest extends WiseTestNG<AjaxElementTest> {
	
	@Page
	private AjaxElementPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page.get();
	}
	
	@Test
	public void shouldFindSeleniumHomePageThroughGoogleUsingPageField() {
		this.page.search("seleniumhq");
		assertTrue(this.page.getSeleniumLink().isDisplayed());
	}
	
}
