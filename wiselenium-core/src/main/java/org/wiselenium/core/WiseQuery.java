package org.wiselenium.core;

import java.util.List;

import org.openqa.selenium.By;

/**
 * Finds wise elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface WiseQuery {
	
	/**
	 * Finds the first element within the current context using the given mechanism.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element. Must be either WebElement or a type annotated with
	 * Field, Container or Frame. Will lookup for its implementation class following
	 * {@link org.wiselenium.core.util.ClasspathUtil#findImplementationClass(Class)} rules.
	 * @param by The locating mechanism to use.
	 * @return The element decorated or null if it shouldn't be decorated because the type didn't
	 * respect the clazz parameter specification.
	 * @since 0.1.0
	 */
	<E> E findElement(Class<E> clazz, By by);
	
	/**
	 * Finds all elements within the current context using the given mechanism.
	 * 
	 * @param <E> The type of the elements.
	 * @param clazz The class of the elements. Must be either WebElement or a type annotated with
	 * Field, Container or Frame. Will lookup for its implementation class following
	 * {@link org.wiselenium.core.util.ClasspathUtil#findImplementationClass(Class)} rules.
	 * @param by The locating mechanism to use.
	 * @return The elements decorated or an empty list if it shouldn't be decorated because the type
	 * didn't respect the clazz parameter specification.
	 * @since 0.1.0
	 */
	<E> List<E> findElements(Class<E> clazz, By by);
	
}
