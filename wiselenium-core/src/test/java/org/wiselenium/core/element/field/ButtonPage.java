package org.wiselenium.core.element.field;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class ButtonPage extends Page<ButtonPage> {
	
	public static final String BUTTON_CLICKED_MESSAGE = "button clicked";
	public static final String BUTTON_VALUE = "button";
	public static final String HIDDEN_BUTTON_VALUE = "hiddenButton";
	public static final String URL = "button.html";
	public static final String TITLE = "page for button tests";
	
	private Button button;
	private Button hiddenButton;
	private WebElement message;
	
	
	public Button getButton() {
		return this.button;
	}
	
	public Button getHiddenButton() {
		return this.hiddenButton;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
}
