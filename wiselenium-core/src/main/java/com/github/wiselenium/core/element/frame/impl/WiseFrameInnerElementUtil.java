/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.core.element.frame.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Utility class for the frames' inner elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameInnerElementUtil {
	
	private WiseFrameInnerElementUtil() {}
	
	/**
	 * Proxies a frame inner element with the specified callback, so that it can be used
	 * transparently.
	 * 
	 * @param <E> The element type.
	 * @param element The frame inner element.
	 * @param callback The callback.
	 * @return The frame inner element proxied.
	 * @since 0.1.0
	 */
	public static <E> E createProxy(E element, Callback callback) {
		Class<?> originalClass = getOriginalClass(element.getClass());
		
		Enhancer e = new Enhancer();
		e.setSuperclass(originalClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(callback);
		
		@SuppressWarnings("unchecked")
		E instance = (E) e.create();
		copyFields(originalClass, element, instance);
		return instance;
	}
	
	/**
	 * Proxies the fields of the object in order to use them transparently even outside the object.
	 * 
	 * @param obj The object.
	 * @since 0.1.0
	 */
	public static void exportFields(Object obj) {
		Class<?> originalClass = getOriginalClass(obj.getClass());
		for (Field field : originalClass.getDeclaredFields())
			exportField(field, obj);
	}
	
	/**
	 * Gets the wrapped element of an element. There's a chance the element is already enhanced, so
	 * it must invoke its proper getWrappedElement() method.
	 * 
	 * @param <E> The element type.
	 * @param element The element.
	 * @return The wrapped element of the element.
	 * @since 0.1.0
	 */
	public static <E> Object getWrappedElement(E element) {
		if (!Enhancer.isEnhanced(element.getClass())) return element;
		return ((WrapsElement) element).getWrappedElement();
	}
	
	/**
	 * Verifies whether a method is the getWrappedElement() method.
	 * 
	 * @param method The method.
	 * @return Whether the method is the getWrappedElement() method.
	 * @since 0.1.0
	 */
	public static boolean isGetWrappedElement(Method method) {
		return "getWrappedElement".equals(method.getName())
			&& method.getReturnType() == WebElement.class && method.getParameterTypes().length == 0;
	}
	
	private static <E> void copyField(Field field, E from, E to) {
		try {
			Field fieldFrom = getOriginalClass(from.getClass()).getDeclaredField(field.getName());
			fieldFrom.setAccessible(true);
			Object fieldFromValue = fieldFrom.get(from);
			
			Field fieldTo = getOriginalClass(to.getClass()).getDeclaredField(field.getName());
			fieldTo.setAccessible(true);
			fieldTo.set(to, fieldFromValue);
		} catch (Exception e) {} // NOSONAR some proxies will throw exception, like jacoco's
	}
	
	private static <E> void copyFields(Class<?> clazz, E from, E to) {
		for (Field field : clazz.getDeclaredFields())
			copyField(field, from, to);
	}
	
	private static void exportField(Field field, Object obj) {
		field.setAccessible(true);
		Object fieldValue;
		try {
			fieldValue = field.get(obj);
			if (fieldValue == null) return;
			
			if (List.class.isAssignableFrom(field.getType()))
				exportList(field, obj, fieldValue);
			else {
				Object proxiedFieldValue = WiseFrameInnerElementFactory.getInstance(fieldValue);
				field.set(obj, proxiedFieldValue);
			}
		} catch (Exception e) {} // NOSONAR some proxies will throw exception, like jacoco's
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void exportList(Field field, Object obj, Object fieldValue)
		throws IllegalAccessException {
		List list = (List) fieldValue;
		for (int i = 0; i < list.size(); i++)
			list.add(WiseFrameInnerElementFactory.getInstance(list.get(i)));
		field.set(obj, list);
	}
	
	private static Class<?> getOriginalClass(Class<?> clazz) {
		Class<?> originalClass = clazz;
		while (Enhancer.isEnhanced(originalClass))
			originalClass = originalClass.getSuperclass();
		return originalClass;
	}
	
}
