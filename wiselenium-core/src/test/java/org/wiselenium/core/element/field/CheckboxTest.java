package org.wiselenium.core.element.field;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class CheckboxTest extends WiseTestNG<CheckboxTest> {
	
	CheckboxPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(CheckboxPage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldCheck() {
		Checkbox checkbox = this.page.getCheckbox();
		checkbox.check();
		assertTrue(checkbox.isChecked());
		checkbox.check();
		assertTrue(checkbox.isChecked());
	}
	
	@Test
	public void shouldClick() {
		Checkbox checkbox = this.page.getCheckbox();
		boolean checkedConditionbeforeClick = checkbox.isChecked();
		checkbox.click();
		assertNotEquals(checkbox.isChecked(), checkedConditionbeforeClick);
	}
	
	@Test
	public void shouldGetChecked() {
		assertFalse(this.page.getCheckbox().isChecked());
		assertTrue(this.page.getCheckbox().check().and().isChecked());
		assertFalse(this.page.getCheckbox().uncheck().and().isChecked());
		
		assertTrue(this.page.getCheckedCheckbox().isChecked());
		assertTrue(this.page.getCheckedCheckedCheckbox().isChecked());
		assertTrue(this.page.getCheckedTrueCheckbox().isChecked());
		assertTrue(this.page.getCheckedFalseCheckbox().isChecked());
	}
	
	@Test
	public void shouldGetEnabled() {
		assertTrue(this.page.getCheckbox().isEnabled());
		assertFalse(this.page.getDisabledCheckbox().isEnabled());
	}
	
	@Test
	public void shouldUncheck() {
		Checkbox checkbox = this.page.getCheckbox();
		checkbox.uncheck();
		assertFalse(checkbox.isChecked());
		checkbox.uncheck();
		assertFalse(checkbox.isChecked());
	}
	
}
