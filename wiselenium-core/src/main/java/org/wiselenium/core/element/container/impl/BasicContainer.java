package org.wiselenium.core.element.container.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.wiselenium.core.element.BasicElement;
import org.wiselenium.core.element.container.Container;
import org.wiselenium.core.pagefactory.WiseLocator;

/**
 * Basic implementation of a common Container. <br/>
 * Should be extended to reflect your own container methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The container type.
 * @since 0.0.1
 */
public class BasicContainer<T extends Container<T>> extends BasicElement<T> implements Container<T> {
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		return WiseLocator.findElement(clazz, by, unwrapWebElement(this));
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		return WiseLocator.findElements(clazz, by, unwrapWebElement(this));
	}
	
}
