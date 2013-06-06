package org.wiselenium.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies Frames. <br/>
 * A Frame is an abstraction of a HTML frame.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Frame {
	
}
