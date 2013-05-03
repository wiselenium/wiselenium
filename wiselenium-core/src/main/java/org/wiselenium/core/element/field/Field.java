package org.wiselenium.core.element.field;

import org.wiselenium.core.element.Element;

/**
 * Represents an abstraction of a HTML Field.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The field type.
 * @since 0.0.1
 */
@org.wiselenium.core.Field
public interface Field<T extends Field<T>> extends Element<T> {
	
	/**
	 * Clicks on this field.
	 * 
	 * @return This field object in order to allow chain calls.
	 * @since 0.0.1
	 */
	T click();
	
	/**
	 * Double clicks on this field.
	 * 
	 * @return This field object in order to allow chain calls.
	 * @since 0.0.1
	 */
	T doubleClick();
	
}
