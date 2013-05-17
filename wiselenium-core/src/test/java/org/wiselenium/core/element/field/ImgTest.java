package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class ImgTest extends TestBase {
	
	ImgPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, ImgPage.class);
		this.page.get();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Img button = this.page.getImg();
		assertEquals(button, button.and());
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
	public void shouldGetAttribute() {
		assertNotNull(this.page.getImg().getAttribute("id"));
		assertNotNull(this.page.getHiddenImg().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getImg().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getHiddenImg().getCssValue("visibility"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getImg().isDisplayed());
		assertFalse(this.page.getHiddenImg().isDisplayed());
	}
	
	@Test
	public void shouldGetId() {
		assertEquals(this.page.getImg().getId(), ImgPage.IMG_ID);
	}
	
	@Test
	public void shouldGetSrc() {
		assertTrue(this.page.getImg().getSrc().endsWith(ImgPage.IMG_SRC));
	}
	
	@Test
	public void shouldGetStyleClass() {
		assertEquals(this.page.getImg().getStyleClass(), ImgPage.IMG_CLASS);
		assertEquals(this.page.getHiddenImg().getStyleClass(), "");
	}
	
	@Test
	public void shouldGetTitle() {
		assertEquals(this.page.getImg().getTitle(), ImgPage.IMG_TITLE);
		assertEquals(this.page.getHiddenImg().getTitle(), "");
	}
	
}
