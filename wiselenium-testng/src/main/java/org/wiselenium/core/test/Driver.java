package org.wiselenium.core.test;

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

/**
 * Enum for the "driver" TestNG parameter value.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public enum Driver {
	
	/**
	 * Corresponds to the ChromeDriver.
	 */
	CHROME {
		
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
		public FirefoxDriver initDriver(Capabilities capabilities) {
			return new FirefoxDriver(capabilities);
		}
	},
	
	/**
	 * Corresponds to the InternetExplorerDriver.
	 */
	IE {
		
		@Override
		public InternetExplorerDriver initDriver(Capabilities capabilities) {
			String driverResource;
			String fileName;
			String osArch = System.getProperty("os.arch");
			if (osArch.endsWith("64")) {
				driverResource = "IEDriverServer-64.exe";
				fileName = "IEDriverServer-64";
			} else {
				driverResource = "IEDriverServer-32.exe";
				fileName = "IEDriverServer-32";
			}
			File fileDriverExe;
			try {
				fileDriverExe = this.copyResourceToTempFile(driverResource, fileName, ".exe");
			} catch (IOException e) {
				throw new DriverInitializationException(driverResource, e);
			}
			System.setProperty("webdriver.ie.driver", fileDriverExe.getAbsolutePath());
			return new InternetExplorerDriver(capabilities);
		}
	};
	
	/**
	 * Should init a webDriver.
	 * 
	 * @param capabilities The desired capabilities for the webDriver.
	 * @return An instance of the webDriver.
	 * @since 0.0.1
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
	 * @since 0.0.1
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
