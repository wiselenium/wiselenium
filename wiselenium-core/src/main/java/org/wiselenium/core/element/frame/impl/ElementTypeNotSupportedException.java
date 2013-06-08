package org.wiselenium.core.element.frame.impl;

import org.wiselenium.core.WiseException;

/**
 * Exception thrown when the element type is not supported by an operation.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
class ElementTypeNotSupportedException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class of the element.
	 */
	public ElementTypeNotSupportedException(Class<?> clazz) {
		super(
			"The element type: "
				+ clazz.getName()
				+ " is not supported. It should be a WebElement or a type annotated with Field, Container or Frame.");
	}
	
}
