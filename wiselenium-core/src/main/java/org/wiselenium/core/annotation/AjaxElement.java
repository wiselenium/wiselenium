package org.wiselenium.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies Ajax Elements. <br/>
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AjaxElement {
	
	/**
	 * The timeout for the element.
	 * 
	 * @return The timeout in seconds.
	 * @since 0.0.1
	 */
	int timeOutInSeconds() default 5; // NOSONAR not really a magic number
	
}
