package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.AnnotationUtils.isAnnotationPresent;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.Container;
import org.wiselenium.core.Field;

/**
 * Factory of frame inner elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseFrameInnerElementFactory {
	
	private WiseFrameInnerElementFactory() {}
	
	/**
	 * Returns a proxy of the element, so that it can be used transparently even outside the frame
	 * class.
	 * 
	 * @param <E> The element type.
	 * @param element The element.
	 * @return The element proxied.
	 * @since 0.0.1
	 */
	@SuppressWarnings("unchecked")
	public static <E> E getInstance(E element) {
		if (isAnnotationPresent(element.getClass(), Field.class))
			return WiseFrameInnerFieldProxy.getInstance(element);
		
		if (isAnnotationPresent(element.getClass(), Container.class))
			return WiseFrameInnerContainerProxy.getInstance(element);
		
		if (isAnnotationPresent(element.getClass(), org.wiselenium.core.Frame.class))
			return WiseFrameInnerFrameProxy.getInstance(element);
		
		if (WebElement.class.isAssignableFrom(element.getClass())) {
			WebElement webElement = (WebElement) element;
			return (E) WiseFrameInnerWebElementProxy.getInstance(webElement);
		}
		
		throw new ElementTypeNotSupportedException(element.getClass());
	}
	
}
