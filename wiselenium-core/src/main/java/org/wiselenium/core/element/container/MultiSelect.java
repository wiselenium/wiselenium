package org.wiselenium.core.element.container;

import java.util.List;

import org.wiselenium.core.element.field.Option;

/**
 * Represents a HTML Multiple Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface MultiSelect extends Container<MultiSelect> {
	
	/**
	 * Deselects all options.
	 * 
	 * @return This select instance to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect deselectAll();
	
	/**
	 * Deselects all options at the given indexes. This is done by examing the "index" attribute of
	 * an element, and not merely by counting.
	 * 
	 * @param indexes The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect deselectByIndex(int... indexes);
	
	/**
	 * Deselects all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param values The values to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect deselectByValue(String... values);
	
	/**
	 * Deselects all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param texts The visible texts to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect deselectByVisibleText(String... texts);
	
	/**
	 * Deselects some options of this select if not already deselected.
	 * 
	 * @param options The options to be deselected.
	 * @return This select instance in order to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect deselectOptions(Option... options);
	
	/**
	 * Gets the options of this select.
	 * 
	 * @return The options of this select.
	 * @since 0.0.1
	 */
	List<Option> getOptions();
	
	/**
	 * Returns the selected options.
	 * 
	 * @return The selected options.
	 * @since 0.0.1
	 */
	List<Option> getSelectedOptions();
	
	/**
	 * Returns the selected values.
	 * 
	 * @return The selected values.
	 * @since 0.0.1
	 */
	List<String> getSelectedValues();
	
	/**
	 * Returns the selected visible texts.
	 * 
	 * @return The selected visible texts.
	 * @since 0.0.1
	 */
	List<String> getSelectedVisibleTexts();
	
	/**
	 * Selects all options.
	 * 
	 * @return This select instance to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect selectAll();
	
	/**
	 * Selects all options at the given indexes. This is done by examing the "index" attribute of an
	 * element, and not merely by counting.
	 * 
	 * @param indexes The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect selectByIndex(int... indexes);
	
	/**
	 * Selects all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param values The values to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect selectByValue(String... values);
	
	/**
	 * Selects all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param texts The visible texts to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect selectByVisibleText(String... texts);
	
	/**
	 * Selects some options of this select if not already selected.
	 * 
	 * @param options The options to be selected.
	 * @return This select instance in order to allow chain calls.
	 * @since 0.0.1
	 */
	MultiSelect selectOptions(Option... options);
	
}
