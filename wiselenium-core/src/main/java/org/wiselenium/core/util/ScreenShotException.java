package org.wiselenium.core.util;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception thrown while taking a snapshot of the webdriver page.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class ScreenShotException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param t The cause of the exception.
	 */
	public ScreenShotException(Throwable t) {
		super("Error while taking a snapshot", t);
	}
	
}
