package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class ImgTest extends WiseTestNG<ImgTest> {
	
	ImgPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(ImgPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldClick() {
		this.page.getImg().click();
		assertEquals(this.page.getMessage(), ImgPage.IMG_CLICKED_MESSAGE);
	}
	
	@Test
	public void shouldGetAlt() {
		assertEquals(this.page.getImg().getAlt(), ImgPage.IMG_ALT);
	}
	
	@Test
	public void shouldGetSrc() {
		assertTrue(this.page.getImg().getSrc().endsWith(ImgPage.IMG_SRC));
	}
	
}
