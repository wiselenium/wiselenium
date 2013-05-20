package org.wiselenium.core.element.container;

import java.util.List;

import org.wiselenium.core.element.field.Option;

/**
 * Represents a HTML Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Select extends Container<Select> {
	
	/**
	 * Gets the options of this select.
	 * 
	 * @return The options of this select.
	 * @since 0.0.1
	 */
	List<Option> getOptions();
	
	/**
	 * Returns the selected option.
	 * 
	 * @return The selected option.
	 * @since 0.0.1
	 */
	Option getSelectedOption();
	
	/**
	 * Returns the selected value.
	 * 
	 * @return The selected value.
	 * @since 0.0.1
	 */
	String getSelectedValue();
	
	/**
	 * Returns the selected visible text.
	 * 
	 * @return The selected visible text.
	 * @since 0.0.1
	 */
	String getSelectedVisibleText();
	
	/**
	 * Selects the option at the given index. This is done by examing the "index" attribute of an
	 * element, and not merely by counting.
	 * 
	 * @param index The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectByIndex(int index);
	
	/**
	 * Selects the option that has a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param value The value to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectByValue(String value);
	
	/**
	 * Selects the option that displays text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param text The visible text to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectByVisibleText(String text);
	
	/**
	 * Selects an option of this select if not already selected.
	 * 
	 * @param option The option to be selected.
	 * @return This select instance in order to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectOption(Option option);
	
}
