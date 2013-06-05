package org.wiselenium.core.element.frame;

import org.wiselenium.core.element.frame.impl.BasicFrame;

@SuppressWarnings("javadoc")
public class FrameC extends BasicFrame<FrameC> {
	
	private FrameCA frame_ca;
	private FrameCB frame_cb;
	
	
	public FrameCA getFrame_ca() {
		return this.exportInnerElement(this.frame_ca);
	}
	
	public String getFrameCATextValue() {
		return this.frame_ca.getTextValue();
	}
	
	public String getFrameCBATextValue() {
		return this.frame_cb.getFrameCBATextValue();
	}
	
	public String getFrameCBBTextValue() {
		return this.frame_cb.getFrameCBBTextValue();
	}
	
	public FrameC sendKeysToFrameCAText(CharSequence... keys) {
		this.frame_ca.sendKeysToText(keys);
		return this;
	}
	
	public FrameC sendKeysToFrameCBAText(CharSequence... keys) {
		this.frame_cb.sendKeysToFrameCBAText(keys);
		return this;
	}
	
	public FrameC sendKeysToFrameCBBText(CharSequence... keys) {
		this.frame_cb.sendKeysToFrameCBBText(keys);
		return this;
	}
	
}
