package org.wiselenium.core.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

@SuppressWarnings("javadoc")
public class WiseTestNG<T extends WiseTestNG<T>> extends WiseTest<T> {
	
	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		WebDriver driver = this.getDriver();
		if (driver != null) driver.quit();
	}
	
}
