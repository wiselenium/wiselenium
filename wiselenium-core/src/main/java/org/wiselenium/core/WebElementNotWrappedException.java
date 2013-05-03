package org.wiselenium.core;

/**
 * Represents an exception thrown by the user attempt to unwrap a WebElement out of a element that
 * doesn't wrap one.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WebElementNotWrappedException extends WiseException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param element The element that doesn't wrap a WebElement.
	 */
	public WebElementNotWrappedException(Object element) {
		super("Element doesn't wrap a WebElement: " + element.toString());
	}
	
}
