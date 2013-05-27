package org.wiselenium.core.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * All wiselenium TestNG tests should extend this class.
 * 
 * @param <T> The type of the test.
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
@Listeners(WiseTestNGListener.class)
public class WiseTestNG<T extends WiseTestNG<T>> extends WiseTest<T> {
	
	private String driverParameter;
	private String urlParameter;
	
	
	@Override
	public String getUrl() {
		if (this.urlParameter == null) return super.getUrl();
		return this.urlParameter;
	}
	
	@Override
	public WebDriver initDriver() {
		if (this.driverParameter == null) return super.initDriver();
		Driver driverEnum = Driver.valueOf(this.driverParameter.toUpperCase());
		return driverEnum.initDriver(this.getDesiredCapabilities());
	}
	
	/**
	 * Prepares the test. Called automatically on the beforeClass lifecyle.
	 * 
	 * @param driver The driver to be used on the test. Can be any of the {@link Driver} values.
	 * @param url The url of the test.
	 * @since 0.0.1
	 */
	@Parameters({ "driver", "url" })
	@BeforeClass
	public void initWiseTestNG(@Optional String driver, @Optional String url) {
		this.driverParameter = driver;
		this.urlParameter = url;
		this.initWiseTest();
	}
	
	/**
	 * Quits the driver of the test. Called automatically on the afterClass lifecycle.
	 * 
	 * @since 0.0.1
	 */
	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		WebDriver driver = this.getDriver();
		if (driver != null) driver.quit();
	}
	
}
