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
package com.github.wiselenium.elements.frame;

import org.openqa.selenium.By;

import com.github.wiselenium.elements.frame.impl.FrameImpl;

@SuppressWarnings("javadoc")
public class FrameC extends FrameImpl<FrameC> {
	
	private FrameCA frame_ca;
	
	
	public String getFrameCATextValue() {
		return this.frame_ca.getTextValue();
	}
	
	public String getFrameCBATextValue() {
		return this.findElement(FrameCB.class, By.name("frame_cb")).getFrameCBATextValue();
	}
	
	public String getFrameCBBTextValue() {
		return this.findElement(FrameCB.class, By.name("frame_cb")).getFrameCBBTextValue();
	}
	
	public FrameC sendKeysToFrameCAText(CharSequence... keys) {
		this.frame_ca.sendKeysToText(keys);
		return this;
	}
	
	public FrameC sendKeysToFrameCBAText(CharSequence... keys) {
		this.findElement(FrameCB.class, By.name("frame_cb")).sendKeysToFrameCBAText(keys);
		return this;
	}
	
	public FrameC sendKeysToFrameCBBText(CharSequence... keys) {
		this.findElement(FrameCB.class, By.name("frame_cb")).sendKeysToFrameCBBText(keys);
		return this;
	}
	
}
