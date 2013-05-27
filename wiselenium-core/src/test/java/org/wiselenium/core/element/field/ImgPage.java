package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class ImgPage extends Page<ImgPage> {
	
	public static final String IMG_CLICKED_MESSAGE = "img clicked";
	public static final String IMG_SRC = "selenium-logo.png";
	public static final String IMG_ALT = "img alt";
	public static final String IMG_TITLE = "img title";
	public static final String IMG_ID = "img";
	public static final String URL = getAbsoluteFilePath("img.html");
	public static final String TITLE = "page for img tests";
	public static final String IMG_CLASS = "any";
	
	private Img img;
	private Img hiddenImg;
	private WebElement message;
	
	
	public Img getHiddenImg() {
		return this.hiddenImg;
	}
	
	public Img getImg() {
		return this.img;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
