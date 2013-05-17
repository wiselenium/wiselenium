package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class TextPage extends Page<TextPage> {
	
	public static final Integer TEXT_MAXLENGTH = 30;
	public static final String TEXT_CLICKED_MESSAGE = "text clicked";
	public static final String TEXT_VALUE = "text";
	public static final String HIDDEN_TEXT_VALUE = "hiddenText";
	public static final String URL = "text.html";
	public static final String TITLE = "page for text tests";
	
	private Text text;
	private Text hiddenText;
	private Text readonlyText;
	private Text readonlyTrueText;
	private Text readonlyFalseText;
	private Text readonlyReadonlyText;
	private WebElement message;
	
	
	public Text getHiddenText() {
		return this.hiddenText;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public Text getReadonlyFalseText() {
		return this.readonlyFalseText;
	}
	
	public Text getReadonlyReadonlyText() {
		return this.readonlyReadonlyText;
	}
	
	public Text getReadonlyText() {
		return this.readonlyText;
	}
	
	public Text getReadonlyTrueText() {
		return this.readonlyTrueText;
	}
	
	public Text getText() {
		return this.text;
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
