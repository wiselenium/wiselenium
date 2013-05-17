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
	 * Retrieves the the maximum number of characters allowed in this text field.
	 * 
	 * @return The maximum number of characters allowed.
	 * @since 0.0.1
	 */
	Integer getMaxLength();
	
	/**
	 * Retrieves the value of this text.
	 * 
	 * @return The innerText of this element.
	 * @since 0.0.1
	 */
	String getValue();
	
	/**
	 * Returns whether this test is read-only or not.
	 * 
	 * @return If this text is read-only.
	 * @since 0.0.1
	 */
	boolean isReadOnly();
	
	/**
	 * Types into this text.
	 * 
	 * @param keysToSend The content to be typed.
	 * @return This text object.
	 * @since 0.0.1
	 */
	Text sendKeys(CharSequence... keysToSend);
	
}
