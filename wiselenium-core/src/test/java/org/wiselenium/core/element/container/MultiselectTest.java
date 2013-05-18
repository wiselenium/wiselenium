package org.wiselenium.core.element.container;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.container.SelectPage.FIRST_OPTION_TEXT;
import static org.wiselenium.core.element.container.SelectPage.FIRST_OPTION_VALUE;
import static org.wiselenium.core.element.container.SelectPage.SECOND_OPTION_TEXT;
import static org.wiselenium.core.element.container.SelectPage.SECOND_OPTION_VALUE;
import static org.wiselenium.core.element.container.SelectPage.THIRD_OPTION_TEXT;
import static org.wiselenium.core.element.container.SelectPage.THIRD_OPTION_VALUE;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class MultiselectTest extends TestBase {
	
	SelectPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, SelectPage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Multiselect select = this.page.getMultiselect();
		assertEquals(select, select.and());
	}
	
	@Test
	public void shouldDeselectByIndex() {
		Multiselect select = this.page.getMultiselect();
		assertFalse(select.selectAll().and().getSelectedValues().isEmpty());
		
		String[] selectedValues = select.deselectByIndex(0, 1).and().getSelectedValues()
			.toArray(new String[0]);
		String[] expectedValues = { THIRD_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldDeselectByValue() {
		Multiselect multiselect = this.page.getMultiselect();
		assertFalse(multiselect.selectAll().and().getSelectedValues().isEmpty());
		
		String[] selectedValues = multiselect.deselectByValue(FIRST_OPTION_VALUE, THIRD_OPTION_TEXT)
			.and().getSelectedValues().toArray(new String[0]);
		String[] expectedValues = { SECOND_OPTION_VALUE, THIRD_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldDeselectByVisibleTexts() {
		Multiselect multiselect = this.page.getMultiselect();
		assertFalse(multiselect.selectAll().and().getSelectedVisibleTexts().isEmpty());
		
		String[] selectedTexts = multiselect
			.deselectByVisibleText(FIRST_OPTION_TEXT, THIRD_OPTION_TEXT).and()
			.getSelectedVisibleTexts().toArray(new String[0]);
		String[] expectedTexts = { SECOND_OPTION_TEXT };
		assertEquals(selectedTexts, expectedTexts);
	}
	
	@Test
	public void shouldGetSelectedVisibleTexts() {
		assertTrue(this.page.getMultiselect().getSelectedVisibleTexts().isEmpty());
		
		String[] selectedTexts = this.page.getMultiselect().selectAll().and()
			.getSelectedVisibleTexts().toArray(new String[0]);
		String[] expectedTexts = { FIRST_OPTION_TEXT, SECOND_OPTION_TEXT, THIRD_OPTION_TEXT };
		assertEquals(selectedTexts, expectedTexts);
	}
	
	@Test
	public void shouldSelectAllAndGetSelectedValuesThenDeselectAll() {
		Multiselect multiselect = this.page.getMultiselect();
		assertTrue(multiselect.getSelectedValues().isEmpty());
		
		String[] selectedValues = multiselect.selectAll().and().getSelectedValues()
			.toArray(new String[0]);
		String[] expectedValues = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE, THIRD_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
		
		multiselect.deselectAll();
		assertTrue(multiselect.getSelectedValues().isEmpty());
	}
	
	@Test
	public void shouldSelectByIndex() {
		Multiselect select = this.page.getMultiselect();
		assertTrue(select.getSelectedValues().isEmpty());
		
		String[] selectedValues = select.selectByIndex(0, 1).and().getSelectedValues()
			.toArray(new String[0]);
		String[] expectedValues = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldSelectByValue() {
		Multiselect select = this.page.getMultiselect();
		assertTrue(select.getSelectedValues().isEmpty());
		
		String[] selectedValues = select.selectByValue(FIRST_OPTION_VALUE, SECOND_OPTION_VALUE)
			.and().getSelectedValues().toArray(new String[0]);
		String[] expectedValues = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldSelectByVisibleText() {
		Multiselect select = this.page.getMultiselect();
		assertTrue(select.getSelectedValues().isEmpty());
		
		String[] selectedTexts = select.selectByVisibleText(FIRST_OPTION_TEXT).and()
			.getSelectedVisibleTexts().toArray(new String[0]);
		String[] expectedTexts = { FIRST_OPTION_TEXT };
		assertEquals(selectedTexts, expectedTexts);
	}
	
}
