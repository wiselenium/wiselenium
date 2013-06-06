package org.wiselenium.core;

import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Interface for "root" types, like a Test or a Page.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The type of the class.
 * @since 0.0.1
 */
public interface WiseRoot<T extends WiseRoot<T>> extends WiseQuery, ScreenShooting<T> {
	
	/**
	 * A helper method for the
	 * {@link WebDriverWait#WebDriverWait(org.openqa.selenium.WebDriver, long)}.
	 * 
	 * @param timeOutInSeconds The timeout in seconds.
	 * @return A WebDriverWait.
	 * @since 0.0.1
	 */
	WebDriverWait waitFor(long timeOutInSeconds);
	
	/**
	 * A helper method for the
	 * {@link WebDriverWait#WebDriverWait(org.openqa.selenium.WebDriver, long, long)}.
	 * 
	 * @param timeOutInSeconds The timeout in seconds.
	 * @param sleepInMillis
	 * @return A WebDriverWait.
	 * @since 0.0.1
	 */
	WebDriverWait waitFor(long timeOutInSeconds, long sleepInMillis);
	
}
