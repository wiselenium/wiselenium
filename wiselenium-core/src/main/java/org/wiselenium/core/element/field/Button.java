package org.wiselenium.core.element.field;

/**
 * Represents a HTML Button.
 * 
 * @author Andre Ricardo Schaffer
 * @since
 */
public interface Button extends Field<Button> {
	
	/**
	 * Retrieves the value of this button.
	 * 
	 * @return The innerText of this button.
	 * @since 0.0.1
	 */
	String getValue();
	
}
