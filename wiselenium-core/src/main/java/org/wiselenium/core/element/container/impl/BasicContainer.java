package org.wiselenium.core.element.container.impl;

import org.wiselenium.core.element.BasicElement;
import org.wiselenium.core.element.container.Container;

/**
 * Basic implementation of a common Container. <br/>
 * Should be extended to reflect your own container methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The container type.
 * @since 0.0.1
 */
public class BasicContainer<T extends Container<T>> extends BasicElement<T> implements Container<T> {
	
}
