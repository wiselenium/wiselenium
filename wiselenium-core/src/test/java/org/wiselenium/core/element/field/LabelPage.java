package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class LabelPage extends Page<LabelPage> {
	
	public static final String LABEL_CLICKED_MESSAGE = "label clicked";
	public static final String LABEL_FOR = "text";
	public static final String LABEL_TEXT = "label text";
	public static final String URL = getAbsolutePath("label.html");
	public static final String TITLE = "page for label tests";
	
	private Label label;
	private Label hiddenLabel;
	private WebElement message;
	
	
	public Label getHiddenLabel() {
		return this.hiddenLabel;
	}
	
	public Label getLabel() {
		return this.label;
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
