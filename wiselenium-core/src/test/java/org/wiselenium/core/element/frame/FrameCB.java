package org.wiselenium.core.element.frame;

import org.wiselenium.core.element.frame.impl.BasicFrame;

@SuppressWarnings("javadoc")
public class FrameCB extends BasicFrame<FrameCB> {
	
	private FrameCBA frame_cba;
	private FrameCBB frame_cbb;
	
	
	public String getFrameCBATextValue() {
		return this.frame_cba.getTextValue();
	}
	
	public String getFrameCBBTextValue() {
		return this.frame_cbb.getTextValue();
	}
	
	public void sendKeysToFrameCBAText(CharSequence... keys) {
		this.frame_cba.sendKeysToText(keys);
	}
	
	public void sendKeysToFrameCBBText(CharSequence... keys) {
		this.frame_cbb.sendKeysToText(keys);
	}
	
}
