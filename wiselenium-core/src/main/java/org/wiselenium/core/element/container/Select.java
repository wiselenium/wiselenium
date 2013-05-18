package org.wiselenium.core.element.container;

/**
 * Represents a HTML Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Select extends Container<Select> {
	
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
	 * Select the option at the given index. This is done by examing the "index" attribute of an
	 * element, and not merely by counting.
	 * 
	 * @param index The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectByIndex(int index);
	
	/**
	 * Select all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param value The value to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectByValue(String value);
	
	/**
	 * Select all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param text The visible text to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.0.1
	 */
	Select selectByVisibleText(String text);
	
}
