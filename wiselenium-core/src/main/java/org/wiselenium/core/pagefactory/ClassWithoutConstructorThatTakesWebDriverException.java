package org.wiselenium.core.pagefactory;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception which indicates that the class being instantiated by wiselenium doesn't
 * have a constructor that takes a WebDriver as only argument.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class ClassWithoutConstructorThatTakesWebDriverException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class that doesn't have a constructor that takes a WebDriver as only
	 * argument.
	 * @param t The cause of the exception.
	 */
	public ClassWithoutConstructorThatTakesWebDriverException(Class<?> clazz, Throwable t) {
		super("The class " + clazz.getName()
			+ "doesn't have a constructor that takes a WebDriver as its only argument", t);
	}
	
}
