package org.wiselenium.core.element.container;

import java.util.List;

/**
 * Represents a HTML Table Foot (tfoot).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface TableFoot extends Container<TableFoot> {
	
	/**
	 * Returns a specific row of the table foot.
	 * 
	 * @param index The index of the row.
	 * @return The row of the table foot.
	 * @since 0.1.0
	 */
	TableRow getRow(int index);
	
	/**
	 * Returns the rows of the table foot.
	 * 
	 * @return The rows of the table foot.
	 * @since 0.1.0
	 */
	List<TableRow> getRows();
	
}
