package org.wiselenium.core.element.field;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("javadoc")
public class ButtonPageWithWebDriverConstructor {
	
	public static final String SUCCESS_MESSAGE = "success";
	public static final String URL = "button.html";
	
	private WebDriver webDriver;
	
	@FindBy(id = "button-id")
	private WebElement button;
	
	private WebElement message;
	
	
	public ButtonPageWithWebDriverConstructor() {}
	
	public ButtonPageWithWebDriverConstructor(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void clickButton() {
		this.button.click();
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public String getTitle() {
		return this.webDriver.getTitle();
	}
	
}
