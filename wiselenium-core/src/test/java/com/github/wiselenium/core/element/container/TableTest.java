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

import static com.github.wiselenium.core.element.container.TablePage.COL_SPAN;
import static com.github.wiselenium.core.element.container.TablePage.ROW_SPAN;
import static com.github.wiselenium.core.element.container.TablePage.TABLE_BODY_CELL1;
import static com.github.wiselenium.core.element.container.TablePage.TABLE_BODY_CELL2;
import static com.github.wiselenium.core.element.container.TablePage.TABLE_CAPTION;
import static com.github.wiselenium.core.element.container.TablePage.TABLE_HEAD_CELL1;
import static com.github.wiselenium.core.element.container.TablePage.TABLE_HEAD_CELL2;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.core.element.container.Table;
import com.github.wiselenium.core.element.container.TableBody;
import com.github.wiselenium.core.element.container.TableCell;
import com.github.wiselenium.core.element.container.TableFoot;
import com.github.wiselenium.core.element.container.TableHead;
import com.github.wiselenium.core.element.container.TableRow;
import com.github.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class TableTest extends WiseTestNG<TableTest> {
	
	TablePage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(TablePage.class).and().get();
	}
	
	@Test
	public void shouldFindElementsInsideCells() {
		Table table = this.page.getComplexTableWithFoot();
		TableBody tbody = table.getBody();
		
		TableRow firstRow = tbody.getRow(0);
		TableCell firstRowSecondCell = firstRow.getCell(1);
		Table innerTable = firstRowSecondCell.findElement(Table.class, By.tagName("table"));
		assertNotNull(innerTable);
		assertFalse(innerTable.getBody().getRows().isEmpty());
		
		TableRow secondRow = tbody.getRow(1);
		TableCell secondRowFirstCell = secondRow.getCell(0);
		WebElement ul = secondRowFirstCell.findElement(WebElement.class, By.tagName("ul"));
		assertNotNull(ul);
	}
	
	@Test
	public void shouldGetBody() {
		Table tableWithCaptionAndHead = this.page.getTableWithCaptionAndHead();
		TableBody body = tableWithCaptionAndHead.getBody();
		assertNotNull(body);
		
		List<TableRow> rows = body.getRows();
		assertFalse(rows.isEmpty());
		
		String[] cellsTexts = { TABLE_BODY_CELL1, TABLE_BODY_CELL2 };
		
		TableRow row = rows.get(0);
		List<TableCell> cells = row.getCells();
		assertFalse(cells.isEmpty());
		for (int i = 0; i < cells.size(); i++) {
			TableCell tableCell = cells.get(i);
			String cellText = cellsTexts[i];
			assertEquals(tableCell.getText(), cellText);
		}
		
		assertNotNull(this.page.getTableWithColspan().getBody());
	}
	
	@Test
	public void shouldGetCaption() {
		Table tableWithCaptionAndHead = this.page.getTableWithCaptionAndHead();
		assertEquals(tableWithCaptionAndHead.getCaption(), TABLE_CAPTION);
		
		assertNull(this.page.getTableWithColspan().getCaption());
	}
	
	@Test
	public void shouldGetColspan() {
		Table tableWithColspan = this.page.getTableWithColspan();
		int cellWithColspan = tableWithColspan.getBody().getRows().get(0).getCells().get(1)
			.getColSpan();
		assertEquals(cellWithColspan, COL_SPAN);
		
		Table tableWithCaptionAndHead = this.page.getTableWithCaptionAndHead();
		int cellWithoutColspan = tableWithCaptionAndHead.getBody().getRows().get(0).getCells()
			.get(0).getColSpan();
		assertEquals(cellWithoutColspan, 1);
	}
	
	@Test
	public void shouldGetFoot() {
		Table complexTableWithFoot = this.page.getComplexTableWithFoot();
		TableFoot foot = complexTableWithFoot.getFoot();
		assertNotNull(foot);
		
		List<TableRow> rows = foot.getRows();
		assertFalse(rows.isEmpty());
		
		String[] cellsTexts = { TablePage.TABLE_FOOT_CELL1, TablePage.TABLE_FOOT_CELL2 };
		
		TableRow row = foot.getRow(0);
		List<TableCell> cells = row.getCells();
		assertFalse(cells.isEmpty());
		for (int i = 0; i < cells.size(); i++) {
			TableCell tableCell = cells.get(i);
			String cellText = cellsTexts[i];
			assertEquals(tableCell.getText(), cellText);
		}
		
		assertNull(this.page.getTableWithColspan().getFoot());
	}
	
	@Test
	public void shouldGetHead() {
		Table tableWithCaptionAndHead = this.page.getTableWithCaptionAndHead();
		TableHead head = tableWithCaptionAndHead.getHead();
		assertNotNull(head);
		
		List<TableRow> rows = head.getRows();
		assertFalse(rows.isEmpty());
		
		String[] cellsTexts = { TABLE_HEAD_CELL1, TABLE_HEAD_CELL2 };
		
		TableRow row = head.getRow(0);
		List<TableCell> cells = row.getCells();
		assertFalse(cells.isEmpty());
		for (int i = 0; i < cells.size(); i++) {
			TableCell tableCell = cells.get(i);
			String cellText = cellsTexts[i];
			assertEquals(tableCell.getText(), cellText);
		}
		
		assertNull(this.page.getTableWithColspan().getHead());
	}
	
	@Test
	public void shouldGetOnlyDirectChildRows() {
		Table table = this.page.getComplexTableWithFoot();
		TableBody tbody = table.getBody();
		
		assertEquals(tbody.getRows().size(), 2);
		assertEquals(tbody.getRow(0).getCells().size(), 2);
		assertEquals(tbody.getRow(1).getCells().size(), 2);
		
		Table innerTable = tbody.getRow(0).getCell(1).findElement(Table.class, By.tagName("table"));
		TableBody innerTableBody = innerTable.getBody();
		assertEquals(innerTableBody.getRows().size(), 2);
		assertEquals(innerTableBody.getRow(0).getCells().size(), 2);
		assertEquals(innerTableBody.getRow(1).getCells().size(), 2);
	}
	
	@Test
	public void shouldGetRowspan() {
		Table tableWithRowspan = this.page.getTableWithRowspan();
		int cellWithRowspan = tableWithRowspan.getBody().getRows().get(1).getCells().get(0)
			.getRowSpan();
		assertEquals(cellWithRowspan, ROW_SPAN);
		
		Table tableWithCaptionAndHead = this.page.getTableWithCaptionAndHead();
		int cellWithoutRowspan = tableWithCaptionAndHead.getBody().getRows().get(0).getCells()
			.get(0).getRowSpan();
		assertEquals(cellWithoutRowspan, 1);
	}
	
}
