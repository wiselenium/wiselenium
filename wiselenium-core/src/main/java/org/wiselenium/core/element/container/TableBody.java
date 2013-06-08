package org.wiselenium.core.element.container;

import java.util.List;

/**
 * Represents a HTML Table Body (tbody).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface TableBody extends Container<TableBody> {
	
	/**
	 * Returns a specific row of the table body.
	 * 
	 * @param index The index of the row.
	 * @return The row of the table body.
	 * @since 0.1.0
	 */
	TableRow getRow(int index);
	
	/**
	 * Returns the rows of the table body.
	 * 
	 * @return The rows of the table body.
	 * @since 0.1.0
	 */
	List<TableRow> getRows();
	
}
