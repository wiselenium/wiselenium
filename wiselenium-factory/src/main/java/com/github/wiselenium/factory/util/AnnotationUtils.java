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
package com.github.wiselenium.factory.util;

import java.lang.annotation.Annotation;

/**
 * Utility class to verify if annotations are present on types.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public final class AnnotationUtils {
	
	private AnnotationUtils() {}
	
	/**
	 * Finds an annotation in a class type hierarchy. <br/>
	 * Code removed from spring-core.
	 * 
	 * @param <A> The annotation type.
	 * @param clazz The class.
	 * @param annotationType The annotation.
	 * @return The annotation found or null.
	 * @since 0.1.0
	 */
	public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType) {
		if (clazz == null) throw new IllegalArgumentException("Clazz parameter can't be null");
		
		A annotation = clazz.getAnnotation(annotationType);
		if (annotation != null) return annotation;
		
		for (Class<?> ifc : clazz.getInterfaces()) {
			annotation = findAnnotation(ifc, annotationType);
			if (annotation != null) return annotation;
		}
		
		if (!Annotation.class.isAssignableFrom(clazz))
			for (Annotation ann : clazz.getAnnotations()) {
				annotation = findAnnotation(ann.annotationType(), annotationType);
				if (annotation != null) return annotation;
			}
		
		Class<?> superClass = clazz.getSuperclass();
		if (superClass == null || superClass == Object.class) return null;
		return findAnnotation(superClass, annotationType);
	}
	
	/**
	 * Verifies if an annotation is present at a class type hierarchy.
	 * 
	 * @param <A> The annotation type.
	 * @param clazz The class.
	 * @param annotationType The annotation.
	 * @return Whether the annotation is present at the class hierarchy or not.
	 * @since 0.1.0
	 */
	public static <A extends Annotation> boolean isAnnotationPresent(Class<?> clazz,
		Class<A> annotationType) {
		
		if (findAnnotation(clazz, annotationType) != null) return true;
		return false;
	}
	
}
