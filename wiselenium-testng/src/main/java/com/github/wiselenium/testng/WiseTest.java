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
package com.github.wiselenium.testng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.github.wiselenium.WiseContext;
import com.github.wiselenium.Wiselenium;
import com.github.wiselenium.factory.WisePageFactory;
import com.github.wiselenium.testng.annotation.Page;
import com.github.wiselenium.testng.exception.PageInjectionException;
import com.github.wiselenium.testng.exception.ScreenShotException;

/**
 * All wiselenium TestNG tests should extend this class.
 * 
 * @param <T> The type of the test.
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
@Listeners(WiseTestListener.class)
public class WiseTest implements WiseQuery, WiseWait, WiseScreenShoot {
	
	private WebDriver driver;
	
	
	@BeforeClass
	public void setUpWiseTest() {
		this.driver = this.initDriver();
		WiseContext.setDriver(this.driver);
		this.injectPageFieldsIntoTest(this);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownWiseTest() {
		WebDriver driver = this.getDriver();
		if (driver != null) driver.quit();
	}
	
	/**
	 * Inits the driver instance for the test. <br/>
	 * May be overridden. By default, inits a FirefoxDriver.
	 * 
	 * @return The instance of the driver for the test.
	 * @since 0.3.0
	 */
	public WebDriver initDriver() {
		return new FirefoxDriver();
	}
	
	private void injectPageFieldsIntoTest(WiseTest testInstance) {
		for (Class<?> clazz = testInstance.getClass(); !clazz.equals(Object.class);
				clazz = clazz.getSuperclass()) {
			
			for (Field field : clazz.getDeclaredFields()) {
				this.injectPageFieldIntoTest(field, testInstance);
			}
		}
	}
	
	private void injectPageFieldIntoTest(Field field, WiseTest testInstance) {
		if (!field.isAnnotationPresent(Page.class)) return;
		
		field.setAccessible(true);
		Object page = testInstance.initElements(field.getType());
		try {
			field.set(testInstance, page);
		} catch (Exception e) {
			throw new PageInjectionException(field.getType(), this.getClass(), e);
		}
	}
	
	
	/**
	 * Returns the driver of the test.
	 * 
	 * @return The driver of the test.
	 * @since 0.3.0
	 */
	public WebDriver getDriver() {
		return this.driver;
	}
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		return Wiselenium.findElement(clazz, by, this.driver);
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		return Wiselenium.findElements(clazz, by, this.driver);
	}
	
	/**
	 * Loads a new web page in the current browser window using a HTTP GET operation.
	 * 
	 * @param url The URL to load. It is best to use a fully qualified URL.
	 * @return This page object.
	 * @since 0.3.0
	 */
	public void get(String url) {
		if (url != null) this.driver.get(url);
	}
	
	/**
	 * Instantiates an instance of the given class, and set a lazy proxy for each of its element
	 * fields. <br/>
	 * 
	 * @param <E> The class type that will be initialized.
	 * @param clazz The class to be initialized.
	 * @return An instance of the class with its element fields proxied.
	 * @since 0.3.0
	 * @see WisePageFactory#initElements(WebDriver, Class)
	 */
	public <E> E initElements(Class<E> clazz) {
		return WisePageFactory.initElements(this.driver, clazz);
	}
	
	/**
	 * As initElements(Class) but will only replace the element fields of an already instantiated
	 * Object.
	 * 
	 * @param <E> The type of the instance.
	 * @param instance The instance whose fields should be proxied.
	 * @return The instance with its element fields proxied.
	 * @since 0.3.0
	 */
	public <E> E initElements(E instance) {
		return WisePageFactory.initElements(this.driver, instance);
	}
	
	@Override
	public WebDriverWait waitFor(long timeOutInSeconds) {
		return new WebDriverWait(this.driver, timeOutInSeconds);
	}
	
	@Override
	public WebDriverWait waitFor(long timeOutInSeconds, long sleepInMillis) {
		return new WebDriverWait(this.driver, timeOutInSeconds, sleepInMillis);
	}
	
	@Override
	public void takeScreenShot(String fileName) {
		File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
		try {
			File destFile = new File(fileName);
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			throw new ScreenShotException(e);
		}
	}
	
	/**
	 * Returns the dir where the test failure screenshots will be saved. <br/>
	 * May be overridden. By default, returns "target/test-failure-screenshots/" .
	 * 
	 * @return The dir where the test failure screenshots will be saved.
	 * @since 0.3.0
	 */
	public String getTestFailureScreenShotPath() {
		return "target/test-failure-screenshots/";
	}
	
}
