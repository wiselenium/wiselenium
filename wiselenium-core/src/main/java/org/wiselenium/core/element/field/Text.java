package org.wiselenium.core.element.field;

/**
 * Represents a HTML input text;
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Text extends Field<Text> {
	
	/**
	 * Clears this text content.
	 * 
	 * @return This text object.
	 * @since 0.0.1
	 */
	Text clear();
	
	/**
	 * Retrieves the value of this text.
	 * 
	 * @return The innerText of this element.
	 * @since 0.0.1
	 */
	String getValue();
	
	/**
	 * Types into this text.
	 * 
	 * @param keysToSend The content to be typed.
	 * @return This text object.
	 * @since 0.0.1
	 */
	Text sendKeys(CharSequence... keysToSend);
	
}
