package org.wiselenium.core.pagefactory;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

/**
 * The wiselenium proxy for pages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WisePageProxy implements MethodInterceptor {
	
	private final WebDriver wrappedDriver;
	
	
	private WisePageProxy(WebDriver driver) {
		this.wrappedDriver = driver;
	}
	
	@SuppressWarnings("unchecked")
	static <T> T getInstance(WebDriver driver, Class<T> clazz) {
		Enhancer e = new Enhancer();
		e.setSuperclass(clazz);
		e.setInterfaces(new Class[] { WrapsDriver.class });
		e.setCallback(new WisePageProxy(driver));
		try {
			return (T) e.create();
		} catch (IllegalArgumentException ex) {
			throw new ClassWithoutNoArgConstructorException(clazz, ex);
		}
	}
	
	private static boolean isGetWrappedDriver(Method method) {
		return "getWrappedDriver".equals(method.getName())
			&& method.getReturnType() == WebDriver.class && method.getParameterTypes().length == 0;
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedDriver(method)) return this.wrappedDriver;
		return proxy.invokeSuper(obj, args);
	}
	
}
