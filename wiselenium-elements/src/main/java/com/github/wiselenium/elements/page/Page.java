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
package com.github.wiselenium.elements.page;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.wiselenium.Wiselenium;
import com.github.wiselenium.elements.WiseQuery;
import com.github.wiselenium.factory.WisePageFactory;
import com.github.wiselenium.factory.annotation.Root;

/**
 * Basic implementation of a common Page. Should be extended to reflect your own page services. <br/>
 * It extends {@link LoadableComponent}, so you can easily take advantage of its loading resources
 * just by overriding the empty load() and isLoaded() methods. <br/>
 * It has a constructor that takes a WebDriver as only argument and another no-arg constructor,
 * allowing you to extend and initialize it with either constructor you prefer.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The page type.
 * @since 0.1.0
 */
public class Page extends LoadableComponent<Page>
implements WrapsDriver, WiseQuery, WiseWait, WiseScreenShoot {
	
	@Root
	private WebDriver driver;
	
	/**
	 * Empty constructor.
	 */
	public Page() {}
	
	/**
	 * Constructor.
	 * @param driver The driver to be encapsulated within the page.
	 */
	public Page(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Instantiates the next page of the user navigation and initialize its elements.
	 * 
	 * @param <E> The type of the page.
	 * @param clazz The class of the page.
	 * @return An instance of the next page of the user navigation.
	 * @since 0.1.0
	 */
	public <E> E initNextPage(Class<E> clazz) {
		return WisePageFactory.initElements(this.driver, clazz);
	}
	
	/**
	 * Executes a script on the page.
	 * 
	 * @param script The script to be executed.
	 * @return The result of the script.
	 * @since 0.1.0
	 */
	public Object executeScript(String script) {
		return ((JavascriptExecutor) this.driver).executeScript(script);
	}
	
	/**
	 * Loads a new web page in the current browser window using a HTTP GET operation.
	 * 
	 * @param url The URL to load. It is best to use a fully qualified URL.
	 * @return This page object.
	 * @since 0.1.0
	 */
	public void get(String url) {
		this.driver.get(url);
	}
	
	/**
	 * Retrieves the current URL that the browser is looking at.
	 * 
	 * @return The current URL.
	 * @since 0.1.0
	 */
	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
	
	/**
	 * Retrieves the source of the page rendered in the browser.
	 * 
	 * @return The source of the page.
	 * @since 0.1.0
	 */
	public String getPageSource() {
		return this.driver.getPageSource();
	}
	
	/**
	 * Retrieves the title of the current page.
	 * 
	 * @return The title of the current page, or null if one is not set.
	 * @since 0.1.0
	 */
	public String getTitle() {
		return this.driver.getTitle();
	}
	
	@Override
	public WebDriver getWrappedDriver() {
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
	
	@Override
	protected void load() {}
	
	@Override
	protected void isLoaded() {}
	
}
