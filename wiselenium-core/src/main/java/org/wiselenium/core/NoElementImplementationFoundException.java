package org.wiselenium.core;

/**
 * Represents an exception thrown when an implementation of the element couldn't be found by
 * wiselenium.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class NoElementImplementationFoundException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param t The cause of the exception.
	 */
	public NoElementImplementationFoundException(Throwable t) {
		super("Couldn't find an implementation for the class", t);
	}
	
}
