package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.FrameUtils.getCurrentFramePath;
import static org.wiselenium.core.FrameUtils.switchToFrame;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtils.getWrappedElement;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtils.isGetWrappedElement;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.wiselenium.core.FrameUtils;

/**
 * The wiselenium proxy for frame inner fields.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseFrameInnerFieldProxy<E> implements MethodInterceptor {
	
	private final E wrappedElement;
	private final List<String> framePath;
	
	
	private WiseFrameInnerFieldProxy(E element) {
		this.wrappedElement = element;
		this.framePath = FrameUtils.getCurrentFramePath();
	}
	
	static <E> E getInstance(E element) {
		return WiseFrameInnerElementUtils.createProxy(element,
			new WiseFrameInnerFieldProxy<E>(element));
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return getWrappedElement(this.wrappedElement);
		
		List<String> currentPath = getCurrentFramePath();
		try {
			switchToFrame(this.framePath);
			return proxy.invokeSuper(obj, args);
		} finally {
			switchToFrame(currentPath);
		}
	}
	
}
