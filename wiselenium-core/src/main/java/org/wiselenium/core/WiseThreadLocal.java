package org.wiselenium.core;

import org.openqa.selenium.WebDriver;

/**
 * Helper class to make a WebDriver instance visible for the whole thread.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WiseThreadLocal {
	
	static final InheritableThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL;
	
	static {
		WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<WebDriver>();
	}
	
	
	private WiseThreadLocal() {}
	
	/**
	 * Retrieves the WebDriver of the current thread.
	 * 
	 * @return The WebDriver of the current thread.
	 * @since 0.0.1
	 */
	public static WebDriver getDriver() {
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
	
	/**
	 * Sets the WebDriver of the current thread.
	 * 
	 * @param driver The WebDriver of the current thread.
	 * @since 0.0.1
	 */
	public static void setDriver(WebDriver driver) {
		WEB_DRIVER_THREAD_LOCAL.set(driver);
	}
	
}
