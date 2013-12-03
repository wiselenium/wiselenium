package com.github.wiselenium.factory;

import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.wiselenium.factory.annotation.Root;
import com.github.wiselenium.factory.exception.ElementInitializationException;

/**
 * Helper class to inject the Root WebElement of an element.
 *
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public final class RootInjector {
	
	private RootInjector() {}
	
	/**
	 * Injects the root webelement into an element.
	 * @param root The root webelement.
	 * @param element The element.
	 */
	public static <E> void rootElement(WebElement root, E element) {
		if (element == null || root == null) return;
		if (element instanceof WebElement) return;
		
		for (Class<?> clazz = element.getClass(); !clazz.equals(Object.class);
				clazz = clazz.getSuperclass()) {
			
			if (Enhancer.isEnhanced(clazz)) continue;
			
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				setRootElementField(root, element, field);
			}
		}
	}
	
	/**
	 * Injects the webdriver into a page.
	 * @param root The webdriver.
	 * @param element The element.
	 */
	public static <E> void rootDriver(WebDriver root, E page) {
		if (page == null || root == null) return;
		
		for (Class<?> clazz = page.getClass(); !clazz.equals(Object.class);
				clazz = clazz.getSuperclass()) {
			
			if (Enhancer.isEnhanced(clazz)) continue;
			
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				setRootDriverField(root, page, field);
			}
		}
	}
	
	private static <E> void setRootElementField(WebElement root, E element, Field field) {
		if (!WebElement.class.isAssignableFrom(field.getType())) return;
		
		Root rootAnnotation = field.getAnnotation(Root.class);
		if (rootAnnotation == null) return;
		
		try {
			field.setAccessible(true);
			field.set(element, root);
		} catch (Exception e) {
			throw new ElementInitializationException(field, element.getClass(), e);
		}
	}
	
	private static <E> void setRootDriverField(WebDriver root, E page, Field field) {
		if (!WebDriver.class.isAssignableFrom(field.getType())) return;
		
		Root rootAnnotation = field.getAnnotation(Root.class);
		if (rootAnnotation == null) return;
		
		try {
			field.setAccessible(true);
			field.set(page, root);
		} catch (Exception e) {
			throw new ElementInitializationException(field, page.getClass(), e);
		}
	}
	
}
