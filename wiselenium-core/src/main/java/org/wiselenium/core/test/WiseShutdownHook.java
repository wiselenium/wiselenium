package org.wiselenium.core.test;

import org.openqa.selenium.WebDriver;

/**
 * Shutdown hook to quit a WebDriver.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class WiseShutdownHook extends Thread {
	
	private final WebDriver driver;
	
	
	WiseShutdownHook(String threadName, WebDriver webDriver) {
		super(threadName);
		this.driver = webDriver;
	}
	
	@Override
	public synchronized void start() {
		if (this.driver != null) this.driver.quit();
	}
	
}
