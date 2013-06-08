package org.wiselenium.core.element.container;

/**
 * Represents a HTML Table.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Table extends Container<Table> {
	
	/**
	 * Returns the body of the table (tbody).
	 * 
	 * @return The body of the table.
	 * @since 0.1.0
	 */
	TableBody getBody();
	
	/**
	 * Returns the caption of the table.
	 * 
	 * @return The caption of the table.
	 * @since 0.1.0
	 */
	String getCaption();
	
	/**
	 * Returns the foot of the table (tfoot).
	 * 
	 * @return The foot of the table.
	 * @since 0.1.0
	 */
	TableFoot getFoot();
	
	/**
	 * Returns the head of the table (thead).
	 * 
	 * @return The head of the table (thead).
	 * @since 0.1.0
	 */
	TableHead getHead();
	
}
