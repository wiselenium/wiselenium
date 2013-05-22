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
	
	private final WebElement wrappedElement;
	private boolean initializedElements;
	
	
	private WiseContainerProxy(WebElement element) {
		this.wrappedElement = element;
	}
	
	static WiseContainerProxy getInstance(WebElement element) {
		return new WiseContainerProxy(element);
	}
	
	private static boolean isGetWrappedElement(Method method) {
		return "getWrappedElement".equals(method.getName())
			&& method.getReturnType() == WebElement.class && method.getParameterTypes().length == 0;
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		this.initializeElements(obj);
		if (isGetWrappedElement(method)) return this.wrappedElement;
		return proxy.invokeSuper(obj, args);
	}
	
	private synchronized void initializeElements(Object obj) {
		if (!this.initializedElements) try {
			this.wrappedElement.toString();
			initElements(this.wrappedElement, obj);
			this.initializedElements = true;
		} catch (NoSuchElementException e) {}
	}
	
}
