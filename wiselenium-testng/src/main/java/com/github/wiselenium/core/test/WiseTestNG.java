/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.core.test;

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
 * @since 0.1.0
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
		return driverEnum.initDriver();
	}
	
	/**
	 * Prepares the test. Called automatically on the beforeClass lifecyle.
	 * 
	 * @param driver The driver to be used on the test. Can be any of the {@link Driver} values.
	 * @param url The url of the test.
	 * @since 0.1.0
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
	 * @since 0.1.0
	 */
	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		WebDriver driver = this.getDriver();
		if (driver != null) driver.quit();
	}
	
}
