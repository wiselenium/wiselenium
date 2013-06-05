package org.wiselenium.core.test;

import static org.testng.Assert.assertNotNull;
import static org.wiselenium.core.test.Driver.CHROME;
import static org.wiselenium.core.test.Driver.FIREFOX;
import static org.wiselenium.core.test.Driver.IE32;
import static org.wiselenium.core.test.Driver.IE64;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class DriverTest {
	
	@Test
	public void shouldOpenChrome() {
		WebDriver driver = CHROME.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldOpenFirefox() {
		WebDriver driver = FIREFOX.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldOpenIE32() {
		WebDriver driver = IE32.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void shouldOpenIE64() {
		WebDriver driver = IE64.initDriver();
		try {
			assertNotNull(driver.getTitle());
		} finally {
			driver.quit();
		}
	}
	
}
