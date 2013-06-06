package org.wiselenium.core.pagefactory;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;

/**
 * Utility class to centralize some wise element proxy tasks.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseElementProxyUtil {
	
	private WiseElementProxyUtil() {}
	
	/**
	 * Verifies whether a method is the getWrappedElement() method.
	 * 
	 * @param method The method.
	 * @return Whether the method is the getWrappedElement() method.
	 * @since 0.0.1
	 */
	public static boolean isGetWrappedElement(Method method) {
		return "getWrappedElement".equals(method.getName())
			&& method.getReturnType() == WebElement.class && method.getParameterTypes().length == 0;
	}
	
}
