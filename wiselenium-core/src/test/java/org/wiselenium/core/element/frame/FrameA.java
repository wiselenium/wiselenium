package org.wiselenium.core.element.frame;

import org.wiselenium.core.element.container.Table;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.frame.impl.BasicFrame;

@SuppressWarnings("javadoc")
public class FrameA extends BasicFrame<FrameA> {
	
	private Text text;
	
	private Table table;
	
	
	public Table getTable() {
		return this.exportInnerElement(this.table);
	}
	
	public Text getText() {
		return this.exportInnerElement(this.text);
	}
	
	public String getTextValue() {
		return this.text.getValue();
	}
	
	public FrameA sendKeysToText(CharSequence... keys) {
		this.text.sendKeys(keys);
		return this;
	}
	
}
