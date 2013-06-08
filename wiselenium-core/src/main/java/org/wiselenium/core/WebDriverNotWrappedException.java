package org.wiselenium.core;

/**
 * Represents an exception thrown by the user attempt to unwrap a WebDriver out of a page that
 * doesn't wrap one.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
class WebDriverNotWrappedException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param page The page that doesn't wrap a WebDriver.
	 */
	public WebDriverNotWrappedException(Object page) {
		super("Page doesn't wrap a WebDriver: " + page.toString());
	}
	
}
