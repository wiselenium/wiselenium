package org.wiselenium.core.element.field;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.Page;

@SuppressWarnings("javadoc")
public class TextPage extends Page<TextPage> {
	
	public static final String TEXT_CLICKED_MESSAGE = "text clicked";
	public static final String TEXT_VALUE = "text";
	public static final String HIDDEN_TEXT_VALUE = "hiddenText";
	public static final String URL = "text.html";
	public static final String TITLE = "page for text tests";
	
	private Text text;
	private Text hiddenText;
	private WebElement message;
	
	
	public Text getHiddenText() {
		return this.hiddenText;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public Text getText() {
		return this.text;
	}
	
}
