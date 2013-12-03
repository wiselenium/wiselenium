package com.github.wiselenium.testng;

import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Waits for specific conditions.
 *
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public interface WiseWait {
	
	/**
	 * Creates a WebDriverWait.
	 * 
	 * @param timeOutInSeconds The timeout in seconds.
	 * @return A WebDriverWait.
	 * @since 0.1.0
	 */
	WebDriverWait waitFor(long timeOutInSeconds);
	
	/**
	 * Creates a WebDriverWait.
	 * 
	 * @param timeOutInSeconds The timeout in seconds.
	 * @param sleepInMillis The polling wait time.
	 * @return A WebDriverWait.
	 * @since 0.1.0
	 */
	WebDriverWait waitFor(long timeOutInSeconds, long sleepInMillis);
	
}
