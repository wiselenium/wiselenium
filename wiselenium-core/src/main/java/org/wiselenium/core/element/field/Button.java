package org.wiselenium.core.element.field;

/**
 * Represents a HTML Button.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Button extends Field<Button> {
	
	/**
	 * Retrieves the type of the button. <br/>
	 * button, reset, submit.
	 * 
	 * @return The type of the button.
	 * @since 0.0.1
	 */
	String getType();
	
	/**
	 * Retrieves the value of this button.
	 * 
	 * @return The innerText of this button.
	 * @since 0.0.1
	 */
	String getValue();
	
}
