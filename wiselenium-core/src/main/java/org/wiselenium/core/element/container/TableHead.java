package org.wiselenium.core.element.container;

import java.util.List;

/**
 * Represents a HTML Table Head (thead).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface TableHead extends Container<TableHead> {
	
	/**
	 * Returns a specific row of the table head.
	 * 
	 * @param index The index of the row.
	 * @return The row of the table head.
	 * @since 0.1.0
	 */
	TableRow getRow(int index);
	
	/**
	 * Returns the rows of the table head.
	 * 
	 * @return The rows of the table head.
	 * @since 0.1.0
	 */
	List<TableRow> getRows();
	
}
