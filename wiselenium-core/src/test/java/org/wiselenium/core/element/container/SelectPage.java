package org.wiselenium.core.element.container;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class SelectPage extends Page<SelectPage> {
	
	public static final String SELECT_CLICKED_MESSAGE = "select clicked";
	public static final String SELECT_FOR = "text";
	public static final String SELECT_TEXT = "select text";
	public static final String URL = "select.html";
	public static final String TITLE = "page for select tests";
	public static final String FIRST_OPTION_VALUE = "value1";
	public static final String SECOND_OPTION_VALUE = "value2";
	public static final String THIRD_OPTION_VALUE = "value3";
	public static final String FIRST_OPTION_TEXT = "text1";
	public static final String SECOND_OPTION_TEXT = "text2";
	public static final String THIRD_OPTION_TEXT = "text3";
	public static final String SELECT_TITLE = "select title";
	public static final String SELECT_CLASS = "any";
	
	private Select select;
	private Select hiddenSelect;
	private MultiSelect multiselect;
	private WebElement message;
	
	
	public Select getHiddenSelect() {
		return this.hiddenSelect;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
	public MultiSelect getMultiselect() {
		return this.multiselect;
	}
	
	public Select getSelect() {
		return this.select;
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
