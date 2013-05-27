package org.wiselenium.testng;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.wiselenium.core.test.WiseTest;

/**
 * All wiselenium TestNG tests should extend this class.
 * 
 * @param <T> The type of the test.
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WiseTestNG<T extends WiseTestNG<T>> extends WiseTest<T> {
	
	/**
	 * Initializes the driver and all other pre-requisites for the test.
	 * 
	 * @since 0.0.1
	 */
	@BeforeClass
	public void initWiseTestNG() {
		this.initWiseTest();
	}
	
	/**
	 * Quits the driver of the test.
	 * 
	 * @since 0.0.1
	 */
	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		WebDriver driver = this.getDriver();
		if (driver != null) driver.quit();
	}
	
}
