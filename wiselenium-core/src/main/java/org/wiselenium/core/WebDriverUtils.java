package org.wiselenium.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Utility class to centralize some webdriver common tasks.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WebDriverUtils {
	
	private WebDriverUtils() {}
	
	/**
	 * Takes a screenshot of the webDriver page. <br/>
	 * Might throw a ScreenShotException.
	 * 
	 * @param driver The webDriver.
	 * @param fileName The name of the screenshot file.
	 * @since 0.0.1
	 */
	public static void takeScreenShot(WebDriver driver, String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			File destFile = new File(fileName);
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			throw new ScreenShotException(e);
		}
	}
	
}
