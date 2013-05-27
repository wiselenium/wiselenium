package org.wiselenium.core.element.container;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class TablePage extends Page<TablePage> {
	
	public static final String TABLE_CAPTION = "Table Headers";
	public static final String TABLE_HEAD_CELL1 = "Name";
	public static final String TABLE_HEAD_CELL2 = "Telephone";
	public static final String TABLE_BODY_CELL1 = "Bill Gates";
	public static final String TABLE_BODY_CELL2 = "555 77 854";
	public static final String TABLE_FOOT_CELL1 = "HELLO";
	public static final String TABLE_FOOT_CELL2 = "WORLD";
	public static final int ROW_SPAN = 2;
	public static final int COL_SPAN = 2;
	public static final String URL = getAbsoluteFilePath("table.html");
	public static final String TITLE = "page for table tests";
	
	private Table tableWithCaptionAndHead;
	private Table tableWithoutHeadButWithHeaderCells;
	private Table tableWithColspan;
	private Table tableWithRowspan;
	private Table complexTableWithFoot;
	
	
	public Table getComplexTableWithFoot() {
		return this.complexTableWithFoot;
	}
	
	public Table getTableWithCaptionAndHead() {
		return this.tableWithCaptionAndHead;
	}
	
	public Table getTableWithColspan() {
		return this.tableWithColspan;
	}
	
	public Table getTableWithoutHeadButWithHeaderCells() {
		return this.tableWithoutHeadButWithHeaderCells;
	}
	
	public Table getTableWithRowspan() {
		return this.tableWithRowspan;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
