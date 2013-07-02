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
package com.github.wiselenium.core.util;

import java.lang.reflect.Modifier;

/**
 * Utility class to find resources on the classpath.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
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
	 * @since 0.1.0
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
			throw new NoElementImplementationClassFoundException(clazz, e);
		}
	}
	
}
