package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * The wiselenium proxy for containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseContainerProxy implements MethodInterceptor {
	
	private static final String GET_WRAPPED_ELEMENT = "getWrappedElement";
	private final WebElement wrappedElement;
	private boolean initializedInnerElements;
	
	
	private WiseContainerProxy(WebElement element) {
		this.wrappedElement = element;
	}
	
	static WiseContainerProxy getInstance(WebElement element) {
		return new WiseContainerProxy(element);
	}
	
	private static boolean isGetWrappedElement(Method method) {
		return GET_WRAPPED_ELEMENT.equals(method.getName());
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		this.initializeInnerElements(obj);
		if (isGetWrappedElement(method)) return this.wrappedElement;
		return proxy.invokeSuper(obj, args);
	}
	
	private synchronized void initializeInnerElements(Object obj) {
		if (!this.initializedInnerElements) try {
			this.wrappedElement.toString();
			initElements(this.wrappedElement, obj);
			this.initializedInnerElements = true;
		} catch (NoSuchElementException e) {}
	}
	
}
