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
package com.github.wiselenium.factory.decorator.frame;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.github.wiselenium.WiseContext;
import com.google.common.collect.Lists;

/**
 * Responsible for handling frames' switches.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public final class FrameSwitcher {
	
	/**
	 * Retrieves the current content path.
	 * 
	 * @return The name|id list of the frames of the current content path.
	 * @since 0.3.0
	 */
	public static List<String> getCurrentFramePath() {
		List<String> currentPath = Lists.newArrayList();
		int depth = 0;
		String currentFrameName = getAscendantFrameName(depth);
		String currentFrameId = getAscendantFrameId(depth);
		while ((currentFrameName != null && !"".equals(currentFrameName))
				|| (currentFrameId != null && !"".equals(currentFrameId))) {
			
			String currentFrameLocator = currentFrameName != null && !"".equals(currentFrameName) ? currentFrameName : currentFrameId;
			currentPath.add(0, currentFrameLocator);
			
			depth++;
			currentFrameName = getAscendantFrameName(depth);
			currentFrameId = getAscendantFrameId(depth);
		}
		
		return currentPath;
	}
	
	/**
	 * Switches the driver to a frame, using its complete path.
	 * 
	 * @param framePath The frame path.
	 * @since 0.3.0
	 */
	public static void switchToFramePath(List<String> framePath) {
		WebDriver driver = WiseContext.getDriver();
		driver.switchTo().defaultContent();
		for (String content : framePath)
			driver.switchTo().frame(content);
	}
	
	private static String getAscendantFrameAttribute(String attribute, int depth) {
		StringBuilder script = new StringBuilder("return self");
		for (int i = 0; i < depth; i++)
			script.append(".parent");
		
		WebDriver driver = WiseContext.getDriver();
		String result;
		try {
			String frameElementScript = script.toString().concat(".frameElement.")
					.concat(attribute);
			result = (String) ((JavascriptExecutor) driver)
					.executeScript(frameElementScript);
		} catch (Exception e) {
			String elementScript = script.toString().concat(".").concat(attribute);
			result = (String) ((JavascriptExecutor) driver).executeScript(elementScript);
		}
		
		return result;
	}
	
	private static String getAscendantFrameId(int depth) {
		return getAscendantFrameAttribute("id", depth);
	}
	
	private static String getAscendantFrameName(int depth) {
		return getAscendantFrameAttribute("name", depth);
	}
	
}
