package org.wiselenium.core.element.field;

/**
 * Represents a HTML Hyperlink.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface Hyperlink extends Field<Hyperlink> {
	
	/**
	 * Retrieves the specified URL of the page the link goes to.
	 * 
	 * @return The href of this link.
	 * @since 0.0.1
	 */
	String getHref();
	
	/**
	 * Retrieves the specification of where to open the linked document.
	 * 
	 * @return The target of this link.
	 * @since 0.0.1
	 */
	String getTarget();
	
	/**
	 * Retrieves the value of this hyperlink.
	 * 
	 * @return The innerText of this hyperlink.
	 * @since 0.0.1
	 */
	String getText();
	
}
