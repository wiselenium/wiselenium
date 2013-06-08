package org.wiselenium.core.element.container;

import java.util.List;

/**
 * Represents a HTML Table Row (tr).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface TableRow extends Container<TableRow> {
	
	/**
	 * Returns a specific cell of the row.
	 * 
	 * @param index The index of the cell.
	 * @return The cell of the row.
	 * @since 0.1.0
	 */
	TableCell getCell(int index);
	
	/**
	 * Returns the cells of the row.
	 * 
	 * @return The cells of the row.
	 * @since 0.1.0
	 */
	List<TableCell> getCells();
	
}
