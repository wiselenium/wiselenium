package org.wiselenium.core.element.field;

/**
 * Represents a HTML Label.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Label extends Field<Label> {
	
	/**
	 * Retrieves the form element id this label is bound to.
	 * 
	 * @return The for attribute of this label.
	 * @since 0.1.0
	 */
	String getFor();
	
	/**
	 * Retrieves the text of this label.
	 * 
	 * @return The text of this label.
	 * @since 0.1.0
	 */
	String getText();
	
}
