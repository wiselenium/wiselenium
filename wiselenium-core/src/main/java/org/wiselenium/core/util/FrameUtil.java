package org.wiselenium.core.util;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.collections.Lists;
import org.wiselenium.core.WiseThreadLocal;

/**
 * Utility class to handle frames.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class FrameUtil {
	
	private FrameUtil() {}
	
	/**
	 * Retrieves the current content path.
	 * 
	 * @return The name|id list of the frames of the current content path.
	 * @since 0.0.1
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
	 * @since 0.0.1
	 */
	public static void switchToFrame(List<String> framePath) {
		WiseThreadLocal.getDriver().switchTo().defaultContent();
		for (String content : framePath)
			WiseThreadLocal.getDriver().switchTo().frame(content);
	}
	
	private static String getAscendantFrameAttribute(String attribute, int depth) {
		StringBuilder script = new StringBuilder("return self");
		for (int i = 0; i < depth; i++)
			script.append(".parent");
		
		String result;
		try {
			String frameElementScript = script.toString().concat(".frameElement.")
				.concat(attribute);
			result = (String) ((JavascriptExecutor) WiseThreadLocal.getDriver())
				.executeScript(frameElementScript);
		} catch (Exception e) {
			String elementScript = script.toString().concat(".").concat(attribute);
			result = (String) ((JavascriptExecutor) WiseThreadLocal.getDriver())
				.executeScript(elementScript);
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
