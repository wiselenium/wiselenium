package org.wiselenium.core;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Hyperlink;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class AjaxElementPage extends Page<AjaxElementPage> {
	
	public static final String URL = "http://www.google.com";
	public static final String TITLE = "Google";
	
	@FindBy(id = "gbqfq")
	private Text text;
	
	@AjaxElement
	@FindBy(xpath = "//a[contains(text(), 'Selenium - Web Browser Automation')]")
	private Hyperlink seleniumLink;
	
	
	public Hyperlink getSeleniumLink() {
		return this.seleniumLink;
	}
	
	public AjaxElementPage search(CharSequence... keysToSend) {
		this.text.sendKeys(keysToSend).and().sendKeys(Keys.ENTER);
		return this;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
