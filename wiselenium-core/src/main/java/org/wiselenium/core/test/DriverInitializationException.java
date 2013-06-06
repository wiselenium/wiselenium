package org.wiselenium.core.test;

import org.wiselenium.core.WiseException;

/**
 * Represents an exception thrown when initializing a WebDriver.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class DriverInitializationException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param driverName The name of the driver.
	 * @param t The cause of the exception.
	 */
	public DriverInitializationException(String driverName, Throwable t) {
		super("Couldn't initialize the driver: " + driverName, t);
	}
	
}
