package org.wiselenium.core;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Utility class to create Pages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WisePageFactory {
	
	/**
	 * Instantiate an instance of the given class, and set a lazy proxy for each of its elements. <br/>
	 * It assumes the element field name as the HTML element's "id" or "name". To change how the
	 * element is located, use the FindBy annotation.
	 * 
	 * @param <T> The class type that will be initialized.
	 * @param driver The driver that will be used to look up the elements of the object.
	 * @param clazz The class to be initialized.
	 * @return An instance of the class with its element fields proxied.
	 * @since 0.0.1
	 * @see FindBy
	 * @see CacheLookup
	 */
	public static <T> T initElements(WebDriver driver, Class<T> clazz) {
		try {
			return createInstanceWithWebDriverConstructor(driver, clazz);
		} catch (ClassWithNoConstructorWithWebDriverException e) {
			return createInstanceWithEmptyConstructor(driver, clazz);
		}
		
		// TODO initialize its elements with the WiseDecorator
	}
	
	private static <T> Enhancer createEnhancerOfInstance(Class<T> clazz) {
		Enhancer e = new Enhancer();
		e.setSuperclass(clazz);
		e.setInterfaces(new Class[] { WrapsDriver.class });
		e.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
				throws Throwable {
				return proxy.invokeSuper(obj, args);
			}
			
		});
		
		return e;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T createInstanceWithEmptyConstructor(WebDriver driver, Class<T> clazz) {
		Enhancer e = createEnhancerOfInstance(clazz);
		Object instance = e.create();
		
		PageFactory.initElements(driver, instance);
		return (T) instance;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T createInstanceWithWebDriverConstructor(WebDriver driver, Class<T> clazz) {
		validateClassHasConstructorWithWebDriver(clazz);
		
		Enhancer e = createEnhancerOfInstance(clazz);
		Object instance = e.create(new Class[] { WebDriver.class }, new Object[] { driver });
		
		PageFactory.initElements(driver, instance);
		return (T) instance;
	}
	
	private static <T> void validateClassHasConstructorWithWebDriver(Class<T> clazz) {
		try {
			clazz.getConstructor(WebDriver.class);
		} catch (Exception e) {
			throw new ClassWithNoConstructorWithWebDriverException(clazz);
		}
	}
	
}
