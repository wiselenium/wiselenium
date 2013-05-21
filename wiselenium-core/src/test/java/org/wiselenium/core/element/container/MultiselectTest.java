package org.wiselenium.core.element.container;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.element.container.SelectPage.FIRST_OPTION_TEXT;
import static org.wiselenium.core.element.container.SelectPage.FIRST_OPTION_VALUE;
import static org.wiselenium.core.element.container.SelectPage.SECOND_OPTION_TEXT;
import static org.wiselenium.core.element.container.SelectPage.SECOND_OPTION_VALUE;
import static org.wiselenium.core.element.container.SelectPage.THIRD_OPTION_TEXT;
import static org.wiselenium.core.element.container.SelectPage.THIRD_OPTION_VALUE;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.field.Option;

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
		MultiSelect select = this.page.getMultiselect();
		assertEquals(select, select.and());
	}
	
	@Test
	public void shouldDeselectByIndex() {
		MultiSelect select = this.page.getMultiselect();
		assertFalse(select.selectAll().and().getSelectedValues().isEmpty());
		
		String[] selectedValues = select.deselectByIndex(0, 1).and().getSelectedValues()
			.toArray(new String[0]);
		String[] expectedValues = { THIRD_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldDeselectByValue() {
		MultiSelect multiselect = this.page.getMultiselect();
		assertFalse(multiselect.selectAll().and().getSelectedValues().isEmpty());
		
		String[] selectedValues = multiselect
			.deselectByValue(FIRST_OPTION_VALUE, THIRD_OPTION_TEXT).and().getSelectedValues()
			.toArray(new String[0]);
		String[] expectedValues = { SECOND_OPTION_VALUE, THIRD_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldDeselectByVisibleTexts() {
		MultiSelect multiselect = this.page.getMultiselect();
		assertFalse(multiselect.selectAll().and().getSelectedVisibleTexts().isEmpty());
		
		String[] selectedTexts = multiselect
			.deselectByVisibleText(FIRST_OPTION_TEXT, THIRD_OPTION_TEXT).and()
			.getSelectedVisibleTexts().toArray(new String[0]);
		String[] expectedTexts = { SECOND_OPTION_TEXT };
		assertEquals(selectedTexts, expectedTexts);
	}
	
	@Test
	public void shouldGetOptions() {
		List<Option> options = this.page.getMultiselect().getOptions();
		assertNotNull(options);
		assertTrue(!options.isEmpty());
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
		MultiSelect multiselect = this.page.getMultiselect();
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
		MultiSelect select = this.page.getMultiselect();
		assertTrue(select.getSelectedValues().isEmpty());
		
		String[] selectedValues = select.selectByIndex(0, 1).and().getSelectedValues()
			.toArray(new String[0]);
		String[] expectedValues = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldSelectByValue() {
		MultiSelect select = this.page.getMultiselect();
		assertTrue(select.getSelectedValues().isEmpty());
		
		String[] selectedValues = select.selectByValue(FIRST_OPTION_VALUE, SECOND_OPTION_VALUE)
			.and().getSelectedValues().toArray(new String[0]);
		String[] expectedValues = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE };
		assertEquals(selectedValues, expectedValues);
	}
	
	@Test
	public void shouldSelectByVisibleText() {
		MultiSelect select = this.page.getMultiselect();
		assertTrue(select.getSelectedValues().isEmpty());
		
		String[] selectedTexts = select.selectByVisibleText(FIRST_OPTION_TEXT).and()
			.getSelectedVisibleTexts().toArray(new String[0]);
		String[] expectedTexts = { FIRST_OPTION_TEXT };
		assertEquals(selectedTexts, expectedTexts);
	}
	
	@SuppressWarnings("null")
	@Test
	public void shouldSelectDeselectAndGetSelectedOptions() {
		MultiSelect select = this.page.getMultiselect();
		List<Option> options = select.getOptions();
		assertTrue(options != null && !options.isEmpty());
		
		String[] values = { FIRST_OPTION_VALUE, SECOND_OPTION_VALUE, THIRD_OPTION_VALUE };
		String[] texts = { FIRST_OPTION_TEXT, SECOND_OPTION_TEXT, THIRD_OPTION_TEXT };
		
		for (int i = 0; i < options.size(); i++) {
			Option option = options.get(i);
			select.selectOptions(option);
			assertTrue(option.isSelected());
			Option selectedOption = select.getSelectedOptions().get(0);
			assertEquals(selectedOption.getValue(), values[i]);
			assertEquals(selectedOption.getVisibleText(), texts[i]);
			select.deselectOptions(option);
			assertFalse(option.isSelected());
		}
		
		select.selectOptions(options.toArray(new Option[0]));
		for (int i = 0; i < options.size(); i++) {
			Option option = options.get(i);
			assertTrue(option.isSelected());
		}
		
		select.deselectOptions(options.toArray(new Option[0]));
		for (int i = 0; i < options.size(); i++) {
			Option option = options.get(i);
			assertFalse(option.isSelected());
		}
	}
	
}
