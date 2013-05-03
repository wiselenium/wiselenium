package org.wiselenium.core;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * The wiselenium proxy for elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseElementProxy implements MethodInterceptor {
	
	private static final String GET_WRAPPED_ELEMENT = "getWrappedElement";
	private final WebElement element;
	
	
	private WiseElementProxy(WebElement element) {
		this.element = element;
	}
	
	static WiseElementProxy getInstance(WebElement element) {
		return new WiseElementProxy(element);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overriden method
	
		if (GET_WRAPPED_ELEMENT.equals(method.getName())) {
			if (!(obj instanceof WrapsElement)) throw new WebElementNotWrappedException(obj);
			return this.element;
		}
		
		return proxy.invokeSuper(obj, args);
	}
	
}
