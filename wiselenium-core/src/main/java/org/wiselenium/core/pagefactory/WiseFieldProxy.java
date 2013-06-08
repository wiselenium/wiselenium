package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.WiseElementProxyUtil.isGetWrappedElement;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * The wiselenium proxy for fields.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFieldProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	
	
	private WiseFieldProxy(WebElement webElement) {
		this.wrappedElement = webElement;
	}
	
	@SuppressWarnings("unchecked")
	static <E> E getInstance(Class<E> implementationClass, WebElement webElement) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implementationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(new WiseFieldProxy(webElement));
		try {
			return (E) e.create();
		} catch (IllegalArgumentException ex) {
			throw new ClassWithoutNoArgConstructorException(implementationClass, ex);
		}
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return this.wrappedElement;
		return proxy.invokeSuper(obj, args);
	}
	
}
