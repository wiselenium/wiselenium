package org.wiselenium.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Utility class to create Pages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class WisePageFactory {
	
	private WisePageFactory() {}
	
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
	 */
	public static <T> T initElements(WebDriver driver, Class<T> clazz) {
		T instance;
		try {
			instance = createInstanceWithWebDriverConstructor(driver, clazz);
		} catch (ClassWithoutConstructorWithWebDriverException e) {
			instance = createInstanceWithEmptyConstructor(driver, clazz);
		}
		
		ElementLocatorFactory locatorFactory = new DefaultElementLocatorFactory(driver);
		WiseDecorator decorator = new WiseDecorator(locatorFactory);
		return initElements(decorator, instance);
	}
	
	private static <T> Enhancer createEnhancerOfInstance(WebDriver driver, Class<T> clazz) {
		Enhancer e = new Enhancer();
		e.setSuperclass(clazz);
		e.setInterfaces(new Class[] { WrapsDriver.class });
		e.setCallback(WisePageProxy.getInstance(driver));
		
		return e;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T createInstanceWithEmptyConstructor(WebDriver driver, Class<T> clazz) {
		Enhancer e = createEnhancerOfInstance(driver, clazz);
		return (T) e.create();
	}
	
	private static <T> T createInstanceWithWebDriverConstructor(WebDriver driver, Class<T> clazz) {
		Constructor<T> constructorWithWebDriver = getConstructorWithWebDriver(clazz);
		try {
			return constructorWithWebDriver.newInstance(driver);
		} catch (Exception e) {
			throw new ClassWithoutConstructorWithWebDriverException(e);
		}
	}
	
	private static <T> Constructor<T> getConstructorWithWebDriver(Class<T> clazz) {
		try {
			return clazz.getConstructor(WebDriver.class);
		} catch (Exception e) {
			throw new ClassWithoutConstructorWithWebDriverException(e);
		}
	}
	
	private static <T> T initElements(FieldDecorator decorator, T instance) {
		Class<?> currentInstanceHierarchyClass = instance.getClass();
		while (currentInstanceHierarchyClass != Object.class) {
			proxyElements(decorator, instance, currentInstanceHierarchyClass);
			currentInstanceHierarchyClass = currentInstanceHierarchyClass.getSuperclass();
		}
		
		return instance;
	}
	
	private static void proxyElements(FieldDecorator decorator, Object instance,
		Class<?> instanceHierarchyClass) {
		
		Field[] fields = instanceHierarchyClass.getDeclaredFields();
		for (Field field : fields) {
			Object value = decorator.decorate(instance.getClass().getClassLoader(), field);
			if (value != null) try {
				field.setAccessible(true);
				field.set(instance, value);
			} catch (IllegalAccessException e) {
				throw new PageCreationException(instance.getClass(), e);
			}
		}
	}
	
}
