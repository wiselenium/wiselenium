package org.wiselenium.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies Containers. <br/>
 * A Container is an abstraction of a block-level HTML element used for grouping other elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Container {
	
}
