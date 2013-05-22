package org.wiselenium.core.element.field;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class RadiobuttonTest extends TestBase {
	
	RadiobuttonPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, RadiobuttonPage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldCheck() {
		Radiobutton radiobutton = this.page.getRadiobutton();
		assertFalse(radiobutton.isChecked());
		
		radiobutton.check();
		assertTrue(radiobutton.isChecked());
		radiobutton.check();
		assertTrue(radiobutton.isChecked());
	}
	
	@Test
	public void shouldClick() {
		Radiobutton radiobutton = this.page.getRadiobutton();
		boolean checkedConditionbeforeClick = radiobutton.isChecked();
		radiobutton.click();
		assertNotEquals(radiobutton.isChecked(), checkedConditionbeforeClick);
	}
	
	@Test
	public void shouldGetChecked() {
		assertFalse(this.page.getRadiobutton().isChecked());
		assertTrue(this.page.getRadiobutton().check().and().isChecked());
		assertFalse(this.page.getDisabledRadiobutton().isChecked());
		
		assertTrue(this.page.getCheckedRadiobutton().isChecked());
		assertTrue(this.page.getCheckedCheckedRadiobutton().isChecked());
		assertTrue(this.page.getCheckedTrueRadiobutton().isChecked());
		assertTrue(this.page.getCheckedFalseRadiobutton().isChecked());
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getRadiobutton().isEnabled());
		assertFalse(this.page.getDisabledRadiobutton().isEnabled());
	}
	
}
