package org.wiselenium.core.element.field;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.wiselenium.core.element.field.impl.RadiobuttonImpl;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class RadiobuttonPage extends Page<RadiobuttonPage> {
	
	public static final String URL = getAbsoluteFilePath("radiobutton.html");
	public static final String TITLE = "page for radiobutton tests";
	
	private Radiobutton radiobutton;
	private Radiobutton disabledRadiobutton;
	private Radiobutton checkedRadiobutton;
	private Radiobutton checkedCheckedRadiobutton;
	private Radiobutton checkedTrueRadiobutton;
	private RadiobuttonImpl checkedFalseRadiobutton;
	
	
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
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
