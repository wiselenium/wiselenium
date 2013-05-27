package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class CheckboxPage extends Page<CheckboxPage> {
	
	public static final String URL = getAbsoluteFilePath("checkbox.html");
	public static final String TITLE = "page for checkbox tests";
	
	private Checkbox checkbox;
	private Checkbox disabledCheckbox;
	private Checkbox checkedCheckbox;
	private Checkbox checkedCheckedCheckbox;
	private Checkbox checkedTrueCheckbox;
	private Checkbox checkedFalseCheckbox;
	
	
	public Checkbox getCheckbox() {
		return this.checkbox;
	}
	
	public Checkbox getCheckedCheckbox() {
		return this.checkedCheckbox;
	}
	
	public Checkbox getCheckedCheckedCheckbox() {
		return this.checkedCheckedCheckbox;
	}
	
	public Checkbox getCheckedFalseCheckbox() {
		return this.checkedFalseCheckbox;
	}
	
	public Checkbox getCheckedTrueCheckbox() {
		return this.checkedTrueCheckbox;
	}
	
	public Checkbox getDisabledCheckbox() {
		return this.disabledCheckbox;
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
