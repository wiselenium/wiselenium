package org.wiselenium.core.element.container;

import java.util.List;

/**
 * Represents a HTML Multiple Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Multiselect extends Container<Multiselect> {
	
	/**
	 * Deselects all options.
	 * 
	 * @return This select instance to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect deselectAll();
	
	/**
	 * Deselects all options at the given indexes. This is done by examing the "index" attribute of
	 * an element, and not merely by counting.
	 * 
	 * @param indexes The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect deselectByIndex(int... indexes);
	
	/**
	 * Deselects all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param values The values to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect deselectByValue(String... values);
	
	/**
	 * Deselects all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param texts The visible texts to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect deselectByVisibleText(String... texts);
	
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
	Multiselect selectAll();
	
	/**
	 * Selects all options at the given indexes. This is done by examing the "index" attribute of an
	 * element, and not merely by counting.
	 * 
	 * @param indexes The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect selectByIndex(int... indexes);
	
	/**
	 * Selects all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param values The values to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect selectByValue(String... values);
	
	/**
	 * Selects all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param texts The visible texts to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Multiselect selectByVisibleText(String... texts);
	
}
