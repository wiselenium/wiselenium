package org.wiselenium.core.pagefactory;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * The wiselenium proxy for containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseContainerProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	private boolean elementsInitialized;
	
	
	private WiseContainerProxy(WebElement webElement) {
		this.wrappedElement = webElement;
	}
	
	@SuppressWarnings("unchecked")
	static <E> E getInstance(Class<E> implementationClass, WebElement webElement) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implementationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(new WiseContainerProxy(webElement));
		
		try {
			return (E) e.create();
		} catch (IllegalArgumentException ex) {
			throw new ClassWithoutNoArgConstructorException(implementationClass, ex);
		}
	}
	
	private static boolean isGetWrappedElement(Method method) {
		return "getWrappedElement".equals(method.getName())
			&& method.getReturnType() == WebElement.class && method.getParameterTypes().length == 0;
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		this.initElements(obj);
		if (isGetWrappedElement(method)) return this.wrappedElement;
		return proxy.invokeSuper(obj, args);
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(this.wrappedElement, obj);
			this.elementsInitialized = true;
		}
	}
	
}
