package org.wiselenium.core.element.container.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.container.Table;
import org.wiselenium.core.element.container.TableBody;
import org.wiselenium.core.element.container.TableFoot;
import org.wiselenium.core.element.container.TableHead;

/**
 * Implementation of a HTML Table.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class TableImpl extends BasicContainer<Table> implements Table {
	
	@FindBy(tagName = "caption")
	private WebElement caption;
	
	@FindBy(tagName = "tbody")
	private TableBody body;
	
	@FindBy(tagName = "tfoot")
	private TableFoot foot;
	
	@FindBy(tagName = "thead")
	private TableHead head;
	
	
	@Override
	public TableBody getBody() {
		return this.body;
	}
	
	@Override
	public String getCaption() {
		try {
			return this.caption.getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Override
	public TableFoot getFoot() {
		try {
			// as it's a proxy, call some method just to return null if couldn't be located
			unwrapWebElement(this.foot).toString();
			return this.foot;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@Override
	public TableHead getHead() {
		try {
			// as it's a proxy, call some method just to return null if couldn't be located
			unwrapWebElement(this.head).toString();
			return this.head;
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
}
