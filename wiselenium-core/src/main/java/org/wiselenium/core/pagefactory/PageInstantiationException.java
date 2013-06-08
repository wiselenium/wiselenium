package org.wiselenium.core.pagefactory;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception thrown due to an error while instantiating a page.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
class PageInstantiationException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param clazz The class of the page.
	 */
	public PageInstantiationException(Class<?> clazz) {
		super(
			"Failure while trying to instantiate the page "
				+ clazz.getName()
				+ ". Make sure it has either a constructor that takes a WebDriver as only argument or a no-arg constructor.");
	}
	
}
