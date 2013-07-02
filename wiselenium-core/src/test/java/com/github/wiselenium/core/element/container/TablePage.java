/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.core.element.container;

import static com.github.wiselenium.core.util.TestResourceFinder.getAbsolutePath;
import static org.testng.Assert.assertEquals;


import com.github.wiselenium.core.element.container.Table;
import com.github.wiselenium.core.pagefactory.Page;

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
	public static final String URL = getAbsolutePath("table.html");
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
