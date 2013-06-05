package org.wiselenium.core.element.frame;

import org.wiselenium.core.WiseQuery;
import org.wiselenium.core.element.Element;

/**
 * Represents an abstraction of a HTML Frame.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The frame type.
 * @since 0.0.1
 */
@org.wiselenium.core.Frame
public interface Frame<T extends Frame<T>> extends Element<T>, WiseQuery {
	
	/**
	 * Proxies an element that lives inside this Frame, so that it can be used transparently even
	 * outside the frame class.
	 * 
	 * @param <E> The type of the element.
	 * @param element The element.
	 * @return The element proxied.
	 * @since 0.0.1
	 */
	<E> E exportInnerElement(E element);
	
}
