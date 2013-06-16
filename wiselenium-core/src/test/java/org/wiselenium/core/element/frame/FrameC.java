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
