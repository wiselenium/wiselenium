package org.wiselenium.core.element.container.impl;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.container.TableFoot;
import org.wiselenium.core.element.container.TableRow;

/**
 * Implementation of a HTML Table Foot (tfoot).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class TableFootImpl extends BasicContainer<TableFoot> implements TableFoot {
	
	@FindBy(xpath = "tr")
	private List<TableRow> rows;
	
	
	@Override
	public TableRow getRow(int index) {
		return this.getRows().get(index);
	}
	
	@Override
	public List<TableRow> getRows() {
		return this.rows;
	}
	
}
