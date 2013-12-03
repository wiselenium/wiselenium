package com.github.wiselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
	
	protected WebDriver driver;
	
	@BeforeClass
	public void initDriver() {
		this.driver = new FirefoxDriver();
		WiseContext.setDriver(this.driver);
	}
	
	@AfterClass(alwaysRun = true)
	public void closeDriver() {
		this.driver.quit();
	}
	
}
