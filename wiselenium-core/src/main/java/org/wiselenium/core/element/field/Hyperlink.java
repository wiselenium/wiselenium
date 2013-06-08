package org.wiselenium.core.element.field;

/**
 * Represents a HTML Hyperlink.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Hyperlink extends Field<Hyperlink> {
	
	/**
	 * Retrieves the specified URL of the page the link goes to.
	 * 
	 * @return The href of this link.
	 * @since 0.1.0
	 */
	String getHref();
	
	/**
	 * Retrieves the specification of where to open the linked document.
	 * 
	 * @return The target of this link.
	 * @since 0.1.0
	 */
	String getTarget();
	
	/**
	 * Retrieves the text of this hyperlink.
	 * 
	 * @return The text of this hyperlink.
	 * @since 0.1.0
	 */
	String getText();
	
}
