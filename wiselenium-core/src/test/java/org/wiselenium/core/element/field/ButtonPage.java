package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

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
	private Button resetButton;
	private Button submitButton;
	private Button disabledButton;
	private WebElement message;
	
	
	public Button getButton() {
		return this.button;
	}
	
	public Button getDisabledButton() {
		return this.disabledButton;
	}
	
	public Button getHiddenButton() {
		return this.hiddenButton;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public Button getResetButton() {
		return this.resetButton;
	}
	
	public Button getSubmitButton() {
		return this.submitButton;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertTrue(this.getWrappedDriver().getCurrentUrl().endsWith(URL));
	}
	
	@Override
	protected void load() {
		this.get(getAbsoluteFilePath(URL));
	}
	
}
