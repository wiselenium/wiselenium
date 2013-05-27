package org.wiselenium.core.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.wiselenium.core.ScreenShooter;
import org.wiselenium.core.WebDriverUtils;
import org.wiselenium.core.WiseQuery;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.pagefactory.WiseLocator;

/**
 * All wiselenium tests should extend this class. <br/>
 * It has no dependency on any test framework (jUnit, TestNG, etc).
 * 
 * @param <T> The type of the test.
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WiseTest<T extends WiseTest<T>> implements WiseQuery, ScreenShooter<T> {
	
	private WebDriver driver;
	
	
	private static void addHookToQuitDriverOnShutdown(WebDriver webDriver) {
		Runtime.getRuntime().addShutdownHook(
			new WiseShutdownHook("wiselenium " + Thread.currentThread().getName(), webDriver));
	}
	
	private static void injectPages(@SuppressWarnings("rawtypes") WiseTest test) {
		// TODO inject pages
		test.toString();
	}
	
	private static void makeDriverVisibleForThread(WebDriver webDriver) {
		WiseThreadLocal.setDriver(webDriver);
	}
	
	/**
	 * Returns this test instance in order to allow chain calls in a more fluent way.
	 * 
	 * @return This test instance.
	 * @since 0.0.1
	 */
	@SuppressWarnings("unchecked")
	public T and() {
		return (T) this;
	}
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		return WiseLocator.findElement(clazz, by, this.getDriver());
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		return WiseLocator.findElements(clazz, by, this.getDriver());
	}
	
	/**
	 * Loads a new web page in the current browser window using a HTTP GET operation.
	 * 
	 * @param url The URL to load. It is best to use a fully qualified URL.
	 * @return This page object.
	 * @since 0.0.1
	 */
	@SuppressWarnings("unchecked")
	public T get(String url) {
		this.getDriver().get(url);
		return (T) this;
	}
	
	/**
	 * On first invocation, 1) calls the initDriver(), 2) makes the driver visible for the whole
	 * thread through the WiseThreadLocal, 3) adds a shutdown hook to quit the driver, 4) injects
	 * all annotated pages into the test instance.
	 * 
	 * @return The driver of the test.
	 * @since 0.0.1
	 */
	public synchronized WebDriver getDriver() {
		if (this.driver == null) {
			this.driver = this.initDriver();
			makeDriverVisibleForThread(this.driver);
			addHookToQuitDriverOnShutdown(this.driver);
			injectPages(this);
		}
		return this.driver;
	}
	
	/**
	 * Should init the driver instance for the test.
	 * 
	 * @return The instance of the driver for the test.
	 * @since 0.0.1
	 */
	public WebDriver initDriver() {
		return new FirefoxDriver();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T takeScreenShot(String fileName) {
		WebDriverUtils.takeScreenShot(this.getDriver(), fileName);
		return (T) this;
	}
}
