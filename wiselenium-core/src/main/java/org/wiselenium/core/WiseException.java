package org.wiselenium.core;

/**
 * Represents a runtime exception thrown by wiselenium.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WiseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param msg The message of the exception.
	 */
	public WiseException(String msg) {
		super(msg);
	}
	
}
