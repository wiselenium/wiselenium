package org.wiselenium.core;

/**
 * Represents an exception which indicates that the class being instantiated by wiselenium doesn't
 * have a constructor that receives a WebDriver.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class ClassWithNoConstructorWithWebDriverException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param <T> The class type.
	 * @param t The cause of the exception.
	 */
	public <T> ClassWithNoConstructorWithWebDriverException(Throwable t) {
		super("The class doesn't have a constructor that receives a WebDriver", t);
	}
	
}
