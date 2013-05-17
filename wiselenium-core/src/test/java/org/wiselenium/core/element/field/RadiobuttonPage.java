package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class RadiobuttonPage extends Page<RadiobuttonPage> {
	
	public static final String URL = "radiobutton.html";
	public static final String TITLE = "page for radiobutton tests";
	
	private Radiobutton radiobutton;
	private Radiobutton disabledRadiobutton;
	private Radiobutton checkedRadiobutton;
	private Radiobutton checkedCheckedRadiobutton;
	private Radiobutton checkedTrueRadiobutton;
	private Radiobutton checkedFalseRadiobutton;
	
	
	public Radiobutton getCheckedCheckedRadiobutton() {
		return this.checkedCheckedRadiobutton;
	}
	
	public Radiobutton getCheckedFalseRadiobutton() {
		return this.checkedFalseRadiobutton;
	}
	
	public Radiobutton getCheckedRadiobutton() {
		return this.checkedRadiobutton;
	}
	
	public Radiobutton getCheckedTrueRadiobutton() {
		return this.checkedTrueRadiobutton;
	}
	
	public Radiobutton getDisabledRadiobutton() {
		return this.disabledRadiobutton;
	}
	
	public Radiobutton getRadiobutton() {
		return this.radiobutton;
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
