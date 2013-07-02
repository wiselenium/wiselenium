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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.core.element.container.Table;
import com.github.wiselenium.core.element.container.TableBody;
import com.github.wiselenium.core.element.container.TableRow;
import com.github.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class BasicContainerTest extends WiseTestNG<BasicContainerTest> {
	
	TablePage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(TablePage.class).and().get();
	}
	
	@Test
	public void shouldAllowChainCallsWithAnd() {
		Table table = this.page.getTableWithCaptionAndHead();
		assertEquals(table, table.and());
	}
	
	@Test
	public void shouldFindElement() {
		Table table = this.page.getTableWithCaptionAndHead();
		TableBody tbody = table.findElement(TableBody.class, By.xpath("tbody"));
		assertNotNull(tbody);
	}
	
	@Test
	public void shouldFindElements() {
		TableBody tbody = this.page.getTableWithCaptionAndHead().getBody();
		List<TableRow> elements = tbody.findElements(TableRow.class, By.xpath("tr"));
		assertFalse(elements.isEmpty());
	}
	
	@Test
	public void shouldGetAttribute() {
		assertNotNull(this.page.getTableWithCaptionAndHead().getAttribute("id"));
	}
	
	@Test
	public void shouldGetCssValue() {
		assertTrue(this.page.getTableWithCaptionAndHead().getCssValue("inexistent").isEmpty());
		assertNotNull(this.page.getTableWithCaptionAndHead().getCssValue("border"));
	}
	
	@Test
	public void shouldGetDisplayCondition() {
		assertTrue(this.page.getTableWithCaptionAndHead().isDisplayed());
	}
	
	@Test
	public void shouldGetId() {
		assertFalse(this.page.getTableWithCaptionAndHead().getId().isEmpty());
	}
	
	@Test
	public void shouldGetStyleClass() {
		assertFalse(this.page.getTableWithCaptionAndHead().getStyleClass().isEmpty());
	}
	
	@Test
	public void shouldGetTitle() {
		assertFalse(this.page.getTableWithCaptionAndHead().getTitle().isEmpty());
	}
	
}
