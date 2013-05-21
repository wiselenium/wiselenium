package org.wiselenium.core.element.container;

import java.util.List;

import org.openqa.selenium.By;
import org.wiselenium.core.element.Element;

/**
 * Represents an abstraction of a HTML Container.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The container type.
 * @since 0.0.1
 */
@org.wiselenium.core.Container
public interface Container<T extends Container<T>> extends Element<T> {
	
	/**
	 * Finds the first element within the current container using the given mechanism.
	 * 
	 * @param <E> The type of the element.
	 * @param clazz The class of the element. Must be either WebElement or a type annotated with
	 * Field, Container or Frame.
	 * @param by The locating mechanism to use.
	 * @return The element decorated or null if it shouldn't be decorated because the type didn't
	 * respect the parameter specification.
	 * @since 0.0.1
	 */
	<E> E findElement(Class<E> clazz, By by);
	
	/**
	 * Finds all elements within the current container using the given mechanism.
	 * 
	 * @param <E> The type of the elements.
	 * @param clazz The class of the elements.
	 * @param by The locating mechanism to use.
	 * @return The elements decorated or an empty list if it shouldn't be decorated because the type
	 * didn't respect the parameter specification.
	 * @since 0.0.1
	 */
	<E> List<E> findElements(Class<E> clazz, By by);
	
}
