package org.wiselenium.core.element.frame.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.collections.Lists;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.element.BasicElement;
import org.wiselenium.core.element.frame.Frame;
import org.wiselenium.core.pagefactory.WiseLocator;

/**
 * Basic implementation of a common Frame. <br/>
 * Should be extended to reflect your own frame methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The frame type.
 * @since 0.1.0
 */
public class BasicFrame<T extends Frame<T>> extends BasicElement<T> implements Frame<T> {
	
	@Override
	public <E> E exportInnerElement(E element) {
		return WiseFrameInnerElementFactory.getInstance(element);
	}
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		E element = WiseLocator.findElement(clazz, by, WiseThreadLocal.getDriver());
		return this.exportInnerElement(element);
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		List<E> proxiedElements = Lists.newArrayList();
		for (E element : WiseLocator.findElements(clazz, by, WiseThreadLocal.getDriver()))
			proxiedElements.add(this.exportInnerElement(element));
		return proxiedElements;
	}
	
}
