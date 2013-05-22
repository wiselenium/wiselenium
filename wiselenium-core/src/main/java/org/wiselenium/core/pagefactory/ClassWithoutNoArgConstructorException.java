package org.wiselenium.core.pagefactory;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception which indicates that the class being instantiated by wiselenium doesn't
 * have a no-arg constructor.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class ClassWithoutNoArgConstructorException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class that doesn't have a no-arg constructor.
	 * @param t The cause of the exception.
	 */
	public ClassWithoutNoArgConstructorException(Class<?> clazz, Throwable t) {
		super("The class " + clazz.getName() + "doesn't have a no-arg constructor", t);
	}
	
}
