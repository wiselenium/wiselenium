package org.wiselenium.core;

/**
 * Represents an exception which indicates that the class being instantiated by wiselenium doesn't
 * have a constructor that receives a WebElement.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class ClassWithNoConstructorWithWebElementException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param <T> The class type.
	 * @param clazz The class that doesn't have a constructor that receives a WebDriver.
	 */
	public <T> ClassWithNoConstructorWithWebElementException(Class<T> clazz) {
		super("The class " + clazz + " doesn't have a constructor that receives a WebElement");
	}
	
}
