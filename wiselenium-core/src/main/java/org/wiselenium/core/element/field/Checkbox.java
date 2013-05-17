package org.wiselenium.core.element.field;

/**
 * Represents a HTML Checkbox.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Checkbox extends Field<Checkbox> {
	
	/**
	 * Checks this checkbox if not checked already.
	 * 
	 * @return This checkbox element to allow chain calls.
	 * @since 0.0.1
	 */
	Checkbox check();
	
	/**
	 * Returns whether this checkbox is checked.
	 * 
	 * @return Whether this checkbox is checked.
	 * @since 0.0.1
	 */
	boolean isChecked();
	
	/**
	 * Returns whether this checkbox is enabled.
	 * 
	 * @return Whether this checkbox is enabled.
	 * @since 0.0.1
	 */
	boolean isEnabled();
	
	/**
	 * Unchecks this checkbox if not unchecked already.
	 * 
	 * @return This checkbox element to allow chain calls.
	 * @since 0.0.1
	 */
	Checkbox uncheck();
	
}
