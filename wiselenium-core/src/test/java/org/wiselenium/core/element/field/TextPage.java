package org.wiselenium.core.element.field;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;

import org.openqa.selenium.WebElement;

@SuppressWarnings("javadoc")
public class TextPage {
	
	public static final String SUCCESS_MESSAGE = "success";
	public static final String URL = "text.html";
	public static final String TITLE = "page for text tests";
	
	// TODO add the hidden button attribute
	
	private Text text;
	private WebElement message;
	
	
	public TextPage and() {
		return this;
	}
	
	public void clickText() {
		this.text.click();
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public String getTextValue() {
		return this.text.getValue();
	}
	
	public String getTitle() {
		return unwrapWebDriver(this).getTitle();
	}
	
	public Text typeOnText(CharSequence... keysToSend) {
		this.text.sendKeys(keysToSend);
		return this.text;
	}
	
}
