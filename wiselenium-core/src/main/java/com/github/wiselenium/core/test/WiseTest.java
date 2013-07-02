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

import static com.github.wiselenium.core.test.Driver.FIREFOX;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.wiselenium.core.WiseRoot;
import com.github.wiselenium.core.WiseThreadLocal;
import com.github.wiselenium.core.pagefactory.PageInitializationException;
import com.github.wiselenium.core.pagefactory.WiseLocator;
import com.github.wiselenium.core.pagefactory.WisePageFactory;
import com.github.wiselenium.core.test.annotation.Page;
import com.github.wiselenium.core.util.ScreenShooter;

/**
 * All wiselenium tests should extend this class. <br/>
 * It has no dependency on any test framework (jUnit, TestNG, etc).
 * 
 * @param <T> The type of the test.
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
@SuppressWarnings("unchecked")
class WiseTest<T extends WiseTest<T>> implements WiseRoot<T> {
	
	private WebDriver driver;
	
	
	private static void addHookToQuitDriverOnShutdown(WebDriver webDriver) {
		Runtime.getRuntime().addShutdownHook(
			new WiseShutdownHook("wiselenium " + Thread.currentThread().getName(), webDriver));
	}
	
	private static void injectPageFieldIntoTest(Field field, WiseTest<?> testInstance) {
		if (!field.isAnnotationPresent(Page.class)) return;
		
		field.setAccessible(true);
		Object page = testInstance.initElements(field.getType());
		try {
			field.set(testInstance, page);
		} catch (Exception e) {
			throw new PageInitializationException(field.getType(), e);
		}
	}
	
	private static void injectPageFieldsIntoTest(WiseTest<?> testInstance) {
		for (Class<?> clazz = testInstance.getClass(); !clazz.equals(Object.class); clazz = clazz
			.getSuperclass())
			for (Field field : clazz.getDeclaredFields())
				injectPageFieldIntoTest(field, testInstance);
	}
	
	private static void makeDriverVisibleForThread(WebDriver webDriver) {
		WiseThreadLocal.setDriver(webDriver);
	}
	
	/**
	 * Returns this test instance in order to allow chain calls in a more fluent way.
	 * 
	 * @return This test instance.
	 * @since 0.1.0
	 */
	public T and() {
		return (T) this;
	}
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		return WiseLocator.findElement(clazz, by, this.driver);
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		return WiseLocator.findElements(clazz, by, this.driver);
	}
	
	/**
	 * Loads a new web page in the current browser window using a HTTP GET operation.
	 * 
	 * @param url The URL to load. It is best to use a fully qualified URL.
	 * @return This page object.
	 * @since 0.1.0
	 */
	public T get(String url) {
		if (url != null) this.driver.get(url);
		return (T) this;
	}
	
	/**
	 * Returns the driver of the test.
	 * 
	 * @return The driver of the test.
	 * @since 0.1.0
	 */
	public WebDriver getDriver() {
		return this.driver;
	}
	
	/**
	 * Returns the dir where the screenshots will be saved. Used on the takeScreenShot(String)
	 * method as the base dir. <br/>
	 * May be overridden. By default, returns "target/tests-screenshots/" .
	 * 
	 * @return The dir where the screenshots will be saved.
	 * @since 0.1.0
	 */
	public String getScreenShotPath() {
		return "target/tests-screenshots/";
	}
	
	/**
	 * Returns the url of the test. Called on a beforeClass lifecycle method to automatically
	 * navigate to its url. <br/>
	 * May be overridden. By default, returns null.
	 * 
	 * @return The url of the test.
	 * @since 0.1.0
	 */
	public String getUrl() {
		return null;
	}
	
	/**
	 * Should init the driver instance for the test. <br/>
	 * May be overridden. By default, inits a FirefoxDriver.
	 * 
	 * @return The instance of the driver for the test.
	 * @since 0.1.0
	 */
	public WebDriver initDriver() {
		return FIREFOX.initDriver();
	}
	
	/**
	 * Instantiates an instance of the given class, and set a lazy proxy for each of its element
	 * fields. <br/>
	 * 
	 * @param <E> The class type that will be initialized.
	 * @param clazz The class to be initialized.
	 * @return An instance of the class with its element fields proxied.
	 * @since 0.1.0
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
	 * @since 0.1.0
	 */
	public <E> E initElements(E instance) {
		return WisePageFactory.initElements(this.driver, instance);
	}
	
	@Override
	public T takeScreenShot(String fileName) {
		ScreenShooter.takeScreenShot(this.driver, this.getScreenShotPath() + fileName);
		return (T) this;
	}
	
	@Override
	public WebDriverWait waitFor(long timeOutInSeconds) {
		return new WebDriverWait(this.driver, timeOutInSeconds);
	}
	
	@Override
	public WebDriverWait waitFor(long timeOutInSeconds, long sleepInMillis) {
		return new WebDriverWait(this.driver, timeOutInSeconds, sleepInMillis);
	}
	
	/**
	 * Must be called by the test on a beforeClass lifecycle method.<br/>
	 * 1) calls the initDriver(), 2) makes the driver visible for the whole thread through the
	 * WiseThreadLocal, 3) adds a shutdown hook to quit the driver, 4) injects all annotated pages
	 * into the test instance, 5) navigates to the url set for the test.
	 * 
	 * @since 0.1.0
	 */
	protected void initWiseTest() {
		this.driver = this.initDriver();
		makeDriverVisibleForThread(this.driver);
		addHookToQuitDriverOnShutdown(this.driver);
		injectPageFieldsIntoTest(this);
		this.get(this.getUrl());
	}
	
}
