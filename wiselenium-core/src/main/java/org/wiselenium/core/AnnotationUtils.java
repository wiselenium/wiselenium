package org.wiselenium.core;

import java.lang.annotation.Annotation;

/**
 * Utility class to verify is an annotation is present on interfaces or types.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class AnnotationUtils {
	
	private AnnotationUtils() {}
	
	/**
	 * Finds an annotation in a class type hierarchy. <br/>
	 * Code removed from spring-core.
	 * 
	 * @param <A> The annotation type.
	 * @param clazz The class.
	 * @param annotationType The annotation.
	 * @return The annotation found or null.
	 * @since 0.0.1
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
	 * @since 0.0.1
	 */
	public static <A extends Annotation> boolean isAnnotationPresent(Class<?> clazz,
		Class<A> annotationType) {
		
		if (findAnnotation(clazz, annotationType) != null) return true;
		return false;
	}
	
}
