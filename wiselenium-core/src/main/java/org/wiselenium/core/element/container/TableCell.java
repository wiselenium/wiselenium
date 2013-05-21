package org.wiselenium.core.element.container;

/**
 * Represents a HTML Table Cell (th or td).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface TableCell extends Container<TableCell> {
	
	/**
	 * Returns the column span of this cell.
	 * 
	 * @return The colspan of this cell.
	 * @since 0.0.1
	 */
	int getColSpan();
	
	/**
	 * Returns the row span of this cell.
	 * 
	 * @return The rowspan of this cell.
	 * @since 0.0.1
	 */
	int getRowSpan();
	
	/**
	 * Returns the text of the cell.
	 * 
	 * @return The text of the cell.
	 * @since 0.0.1
	 */
	String getText();
	
}
