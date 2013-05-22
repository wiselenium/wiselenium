package org.wiselenium.core.element.container.impl;

import static java.lang.Integer.valueOf;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.wiselenium.core.element.container.TableCell;

/**
 * Implementation of a HTML Table Cell (th or td).
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class TableCellImpl extends BasicContainer<TableCell> implements TableCell {
	
	@Override
	public int getColSpan() {
		String colspan = this.getAttribute("colspan");
		if (colspan == null) return 1;
		return valueOf(colspan);
	}
	
	@Override
	public int getRowSpan() {
		String rowspan = this.getAttribute("rowspan");
		if (rowspan == null) return 1;
		return valueOf(rowspan);
	}
	
	@Override
	public String getText() {
		return unwrapWebElement(this).getText();
	}
	
}
