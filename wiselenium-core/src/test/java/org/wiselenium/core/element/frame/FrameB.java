package org.wiselenium.core.element.frame;

import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.frame.impl.BasicFrame;

@SuppressWarnings("javadoc")
public class FrameB extends BasicFrame<FrameB> {
	
	private Text text;
	
	
	public String getTextValue() {
		return this.text.getValue();
	}
	
	public FrameB sendKeysToText(CharSequence... keys) {
		this.text.sendKeys(keys);
		return this;
	}
	
}
