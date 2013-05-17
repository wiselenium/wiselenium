package org.wiselenium.core.element.field;

/**
 * Represents a HTML Radio button.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Radiobutton extends Field<Radiobutton> {
	
	/**
	 * Checks this radio button if not checked already.
	 * 
	 * @return This radio button element to allow chain calls.
	 * @since 0.0.1
	 */
	Radiobutton check();
	
	/**
	 * Returns whether this radio button is checked.
	 * 
	 * @return Whether this radio button is checked.
	 * @since 0.0.1
	 */
	boolean isChecked();
	
	/**
	 * Returns whether this radio button is enabled.
	 * 
	 * @return Whether this radio button is enabled.
	 * @since 0.0.1
	 */
	boolean isEnabled();
	
}
