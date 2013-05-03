package org.wiselenium.core;

import java.lang.reflect.Modifier;

/**
 * Utility class to help find resources on the classpath.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public final class ClasspathUtil {
	
	private ClasspathUtil() {}
	
	/**
	 * Finds the implementation class of a type on the classpath. <br/>
	 * If the class is already a concrete class, returns itself. Otherwise, it only searchs for
	 * implementation classes that match the pattern classPackage.{impl}.className{Impl} yet. A more
	 * sofisticated search is planned to be implemented on the future.
	 * 
	 * @param <T> The class type.
	 * @param clazz The class.
	 * @return The implementation of the class.
	 * @since 0.0.1
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> findImplementationClass(Class<T> clazz) {
		if (!Modifier.isAbstract(clazz.getModifiers())) return clazz;
		
		StringBuilder implementationClass = new StringBuilder();
		implementationClass.append(clazz.getPackage().getName());
		implementationClass.append(".impl.");
		implementationClass.append(clazz.getSimpleName());
		implementationClass.append("Impl");
		
		try {
			return (Class<? extends T>) Class.forName(implementationClass.toString());
		} catch (ClassNotFoundException e) {
			throw new NoElementImplementationFoundException(clazz);
		}
	}
	
}
