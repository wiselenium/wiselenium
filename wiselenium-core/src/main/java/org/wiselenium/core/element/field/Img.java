package org.wiselenium.core.element.field;

/**
 * Represents a HTML Image.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Img extends Field<Img> {
	
	/**
	 * Retrieves the alt of this image.
	 * 
	 * @return The alt of this image.
	 * @since 0.1.0
	 */
	String getAlt();
	
	/**
	 * Retrieves the src of this image.
	 * 
	 * @return The src of this image.
	 * @since 0.1.0
	 */
	String getSrc();
	
}
