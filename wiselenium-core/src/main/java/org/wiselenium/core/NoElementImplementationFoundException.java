package org.wiselenium.core;

/**
 * Represents an exception thrown when an implementation of the element couldn't be found by
 * wiselenium.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class NoElementImplementationFoundException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class whose implementation couldn't be found.
	 */
	public NoElementImplementationFoundException(Class<?> clazz) {
		super("Couldn't find an implementation of the class " + clazz.getName());
	}
	
}
