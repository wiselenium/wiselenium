package org.wiselenium.core;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@SuppressWarnings("javadoc")
public class TestBase {
	
	protected WebDriver driver;
	
	
	protected static String getAbsoluteFilePath(String relativePath) {
		return "file:///" + new File("").getAbsolutePath() + "/src/test/resources/" + relativePath;
	}
	
	@AfterClass(alwaysRun = true)
	protected void closeBrowser() {
		this.driver.quit();
	}
	
	@BeforeClass
	protected void openBrowser() {
		this.driver = new FirefoxDriver();
	}
	
}
