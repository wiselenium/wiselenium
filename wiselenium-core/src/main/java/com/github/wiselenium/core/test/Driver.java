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

import static org.openqa.selenium.ie.InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * WebDrivers enum for convenience.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public enum Driver {
	
	/**
	 * Corresponds to the ChromeDriver.
	 */
	CHROME {
		
		@Override
		public ChromeDriver initDriver() {
			return this.initDriver(DesiredCapabilities.chrome());
		}
		
		@Override
		public ChromeDriver initDriver(Capabilities capabilities) {
			String driverResource = "chromedriver.exe";
			File fileDriverExe;
			try {
				fileDriverExe = this.copyResourceToTempFile(driverResource, "chromedriver", ".exe");
			} catch (IOException e) {
				throw new DriverInitializationException(driverResource, e);
			}
			System.setProperty("webdriver.chrome.driver", fileDriverExe.getAbsolutePath());
			return new ChromeDriver(capabilities);
		}
	},
	
	/**
	 * Corresponds to the FirefoxDriver.
	 */
	FIREFOX {
		
		@Override
		public FirefoxDriver initDriver() {
			return this.initDriver(DesiredCapabilities.firefox());
		}
		
		@Override
		public FirefoxDriver initDriver(Capabilities capabilities) {
			return new FirefoxDriver(capabilities);
		}
	},
	
	/**
	 * Corresponds to the 32-bit InternetExplorerDriver.
	 */
	IE32 {
		
		@Override
		public InternetExplorerDriver initDriver() {
			return this.initDriver(DesiredCapabilities.internetExplorer());
		}
		
		@Override
		public InternetExplorerDriver initDriver(Capabilities capabilities) {
			String driverResource = "IEDriverServer-32.exe";
			File fileDriverExe;
			try {
				fileDriverExe = this.copyResourceToTempFile(driverResource, "IEDriverServer-32",
					".exe");
			} catch (IOException e) {
				throw new DriverInitializationException(driverResource, e);
			}
			System.setProperty("webdriver.ie.driver", fileDriverExe.getAbsolutePath());
			return new InternetExplorerDriver(this.hackCapabilities(capabilities));
		}
		
		private Capabilities hackCapabilities(Capabilities capabilities) {
			if (capabilities instanceof DesiredCapabilities)
				((DesiredCapabilities) capabilities).setCapability(
					INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
			return capabilities;
		}
		
	},
	
	/**
	 * Corresponds to the 64-bit InternetExplorerDriver.
	 */
	IE64 {
		
		@Override
		public InternetExplorerDriver initDriver() {
			return this.initDriver(DesiredCapabilities.internetExplorer());
		}
		
		@Override
		public InternetExplorerDriver initDriver(Capabilities capabilities) {
			String driverResource = "IEDriverServer-64.exe";
			File fileDriverExe;
			try {
				fileDriverExe = this.copyResourceToTempFile(driverResource, "IEDriverServer-64",
					".exe");
			} catch (IOException e) {
				throw new DriverInitializationException(driverResource, e);
			}
			System.setProperty("webdriver.ie.driver", fileDriverExe.getAbsolutePath());
			return new InternetExplorerDriver(this.hackCapabilities(capabilities));
		}
		
		private Capabilities hackCapabilities(Capabilities capabilities) {
			if (capabilities instanceof DesiredCapabilities)
				((DesiredCapabilities) capabilities).setCapability(
					INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
			return capabilities;
		}
		
	};
	
	/**
	 * Should init a webDriver.
	 * 
	 * @return An instance of the webDriver.
	 * @since 0.1.0
	 */
	public abstract WebDriver initDriver();
	
	/**
	 * Should init a webDriver with the desired capabilities.
	 * 
	 * @param capabilities The desired capabilities for the webDriver.
	 * @return An instance of the webDriver.
	 * @since 0.1.0
	 */
	public abstract WebDriver initDriver(Capabilities capabilities);
	
	/**
	 * Copies a resource to a temp file.
	 * 
	 * @param resourceName The name of the resource.
	 * @param tempFileName The name of the temp file.
	 * @param tempFileExtension The extension of the temp file.
	 * @return The temp file.
	 * @throws IOException When there's an error while copying the resource.
	 * @since 0.1.0
	 */
	protected File copyResourceToTempFile(String resourceName, String tempFileName,
		String tempFileExtension) throws IOException {
		InputStream resourceInputStream = null;
		OutputStream fileOutputStream = null;
		File file;
		try {
			resourceInputStream = this.getClass().getClassLoader()
				.getResourceAsStream(resourceName);
			file = File.createTempFile(tempFileName, tempFileExtension);
			file.deleteOnExit();
			fileOutputStream = new FileOutputStream(file);
			IOUtils.copy(resourceInputStream, fileOutputStream);
		} finally {
			IOUtils.closeQuietly(resourceInputStream);
			IOUtils.closeQuietly(fileOutputStream);
		}
		return file;
	}
	
}
