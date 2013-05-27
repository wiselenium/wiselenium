package org.wiselenium.core.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * All wiselenium TestNG tests should extend this class.
 * 
 * @param <T> The type of the test.
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
@Listeners(WiseTestNGListener.class)
public class WiseTestNG<T extends WiseTestNG<T>> extends WiseTest<T> {
	
	@Override
	@BeforeClass
	public void initWiseTest() {
		super.initWiseTest();
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
