package org.wiselenium.core.element.frame;

import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.frame.impl.BasicFrame;

@SuppressWarnings("javadoc")
public class FrameCBB extends BasicFrame<FrameCBB> {
	
	private Text text;
	
	
	public String getTextValue() {
		return this.text.getValue();
	}
	
	public FrameCBB sendKeysToText(CharSequence... keys) {
		this.text.sendKeys(keys);
		return this;
	}
	
}
