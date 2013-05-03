package org.wiselenium.core.element;


/**
 * Represents a HTML Element.
 * 
 * @author Andre Ricardo Schaffer
 * @since
 */
public interface Element {
	
	/**
	 * Returns this element object in order to chain calls in a more fluent way.
	 * 
	 * @return This element object.
	 * @since 0.0.1
	 */
	Element and();
	
	/**
	 * Gets the value of a given attribute of the element.
	 * 
	 * @param name The name of the attribute.
	 * @return The attribute's current value or null if the value is not set.
	 * @since 0.0.1
	 */
	String getAttribute(String name);
	
	/**
	 * Gets the value of a given CSS property.
	 * 
	 * @param propertyName The name of the property.
	 * @return The current, computed value of the property.
	 * @since 0.0.1
	 */
	String getCssValue(String propertyName);
	
	/**
	 * Verifies if the element is displayed on the page.
	 * 
	 * @return Whether or not the element is displayed.
	 * @since 0.0.1
	 */
	boolean isDisplayed();
	
}
