package org.wiselenium.core.element.field;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("javadoc")
public class ButtonPage {
	
	public static final String SUCCESS_MESSAGE = "success";
	public static final String URL = "button.html";
	
	@FindBy(id = "button-id")
	private WebElement button;
	
	private WebElement message;
	
	
	public void clickButton() {
		this.button.click();
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public String getTitle() {
		return unwrapWebDriver(this).getTitle();
	}
	
}
