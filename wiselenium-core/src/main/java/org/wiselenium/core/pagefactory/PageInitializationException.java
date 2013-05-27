package org.wiselenium.core.pagefactory;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception thrown due to an error while initializing the elements of a page.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class PageInitializationException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class of the page.
	 * @param t The cause of the exception.
	 */
	public PageInitializationException(Class<?> clazz, Throwable t) {
		super("Failure while trying to initialize the elements of the page " + clazz.getName(), t);
	}
	
}
