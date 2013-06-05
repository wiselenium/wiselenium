package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.FrameUtils.getCurrentFramePath;
import static org.wiselenium.core.FrameUtils.switchToFrame;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtils.getWrappedElement;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtils.isGetWrappedElement;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtils.exportFields;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.wiselenium.core.FrameUtils;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.pagefactory.WisePageFactory;

/**
 * The wiselenium proxy for frame inner containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseFrameInnerContainerProxy<E> implements MethodInterceptor {
	
	private final E wrappedElement;
	private final List<String> framePath;
	private boolean elementsInitialized;
	
	
	private WiseFrameInnerContainerProxy(E element) {
		this.wrappedElement = element;
		this.framePath = FrameUtils.getCurrentFramePath();
	}
	
	static <E> E getInstance(E element) {
		return WiseFrameInnerElementUtils.createProxy(element,
			new WiseFrameInnerContainerProxy<E>(element));
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return getWrappedElement(this.wrappedElement);
		
		List<String> currentPath = getCurrentFramePath();
		try {
			switchToFrame(this.framePath);
			this.initElements(obj);
			return proxy.invokeSuper(obj, args);
		} finally {
			switchToFrame(currentPath);
		}
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(WiseThreadLocal.getDriver(), obj);
			exportFields(obj);
			this.elementsInitialized = true;
		}
	}
	
}
