package org.wiselenium.core.element.field;

/**
 * Represents a HTML Select Option.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Option extends Field<Option> {
	
	/**
	 * Gets the value of this option.
	 * 
	 * @return The value of this option.
	 * @since 0.1.0
	 */
	String getValue();
	
	/**
	 * Gets the visible text of this option.
	 * 
	 * @return The visible text of this option.
	 * @since 0.1.0
	 */
	String getVisibleText();
	
	/**
	 * Verifies whether this option is selected.
	 * 
	 * @return Whether this option is selected.
	 * @since 0.1.0
	 */
	boolean isSelected();
	
}
