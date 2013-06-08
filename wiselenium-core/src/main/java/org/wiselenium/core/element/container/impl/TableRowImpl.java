package org.wiselenium.core.element.container.impl;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.container.TableCell;
import org.wiselenium.core.element.container.TableRow;

/**
 * Implementation of a HTML Table Row (tr).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class TableRowImpl extends BasicContainer<TableRow> implements TableRow {
	
	@FindBy(xpath = "th|td")
	private List<TableCell> cells;
	
	
	@Override
	public TableCell getCell(int index) {
		return this.getCells().get(index);
	}
	
	@Override
	public List<TableCell> getCells() {
		return this.cells;
	}
	
}
