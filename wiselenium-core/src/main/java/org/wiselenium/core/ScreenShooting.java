package org.wiselenium.core;

/**
 * Takes a screenshot of the browser.
 * 
 * @param <T> The type of the screenshooter.
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface ScreenShooting<T> {
	
	/**
	 * Takes a screenshot of the webDriver page and saves it into a file.
	 * 
	 * @param fileName The name of the screenshot file.
	 * @return This instance in order to allow chain calls.
	 * @since 0.1.0
	 */
	T takeScreenShot(String fileName);
	
}
