package org.wiselenium.core;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents a common Page. <br/>
 * Should be extended to reflect your own page services.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class Page {
	
	private WebDriver driver;
	
	
	// TODO add a getDefaultBaseUrl method?
	
	/**
	 */
	public Page() {}
	
	/**
	 * @param driver The driver to be encapsulated within the page.
	 */
	public Page(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Returns this page object in order to allow chain calls in a more fluent way.
	 * 
	 * @return This page object.
	 * @since 0.0.1
	 */
	public Page and() {
		return this;
	}
	
	/**
	 * Executes a script on the page.
	 * 
	 * @param script The script to be executed.
	 * @return The result of the script.
	 * @since 0.0.1
	 */
	public Object executeScript(String script) {
		return ((JavascriptExecutor) this.getWebDriver()).executeScript(script);
	}
	
	/**
	 * Find all elements within the current page using the given mechanism. <br/>
	 * 
	 * @param by The locating mechanism to use
	 * @return A list of all WebElements found or an empty list if nothing matches.
	 * @since 0.0.1
	 */
	public List<WebElement> findElements(By by) {
		return this.getWebDriver().findElements(by);
	}
	
	/**
	 * Loads a new web page in the current browser window using a HTTP GET operation.
	 * 
	 * @param url The URL to load. It is best to use a fully qualified URL
	 * @since 0.0.1
	 */
	public void get(String url) {
		this.getWebDriver().get(url);
	}
	
	/**
	 * Retrieves the current URL that the browser is looking at.
	 * 
	 * @return The current URL.
	 * @since 0.0.1
	 */
	public String getCurrentUrl() {
		return this.getWebDriver().getCurrentUrl();
	}
	
	/**
	 * Retrieves the source of the page rendered in the browser.
	 * 
	 * @return The source of the page.
	 * @since 0.0.1
	 */
	public String getPageSource() {
		return this.getWebDriver().getPageSource();
	}
	
	/**
	 * Retrieves the title of the current page.
	 * 
	 * @return The title of the current page, or null if one is not set.
	 * @since 0.0.1
	 */
	public String getTitle() {
		return this.getWebDriver().getTitle();
	}
	
	/**
	 * Retrieves the WebDriver encapsulated within the page.
	 * 
	 * @return The driver of the page.
	 * @since 0.0.1
	 */
	public WebDriver getWebDriver() {
		if (this.driver != null) return this.driver;
		return unwrapWebDriver(this);
	}
	
}
