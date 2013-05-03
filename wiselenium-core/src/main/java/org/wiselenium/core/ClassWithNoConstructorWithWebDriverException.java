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
	 * @param clazz The class that doesn't have a constructor that receives a WebDriver.
	 */
	public <T> ClassWithNoConstructorWithWebDriverException(Class<T> clazz) {
		super("The class " + clazz + " doesn't have a constructor that receives a WebDriver");
	}
	
}
