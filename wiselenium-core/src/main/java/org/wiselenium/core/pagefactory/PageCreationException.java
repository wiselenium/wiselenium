package org.wiselenium.core.pagefactory;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception thrown due to an error while creating a page object.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class PageCreationException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class of the page.
	 * @param t The cause of the exception.
	 */
	public PageCreationException(Class<? extends Object> clazz, Throwable t) {
		super("Failure while trying to create the page " + clazz.getName(), t);
	}
	
}
