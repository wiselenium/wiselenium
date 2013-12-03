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


import java.util.List;

import org.openqa.selenium.By;

import com.github.wiselenium.elements.component.Radiobutton;
import com.github.wiselenium.elements.component.Text;
import com.github.wiselenium.elements.frame.impl.FrameImpl;

@SuppressWarnings("javadoc")
public class FrameB extends FrameImpl<FrameB> {
	
	private Text text;
	
	
	public String getTextValue() {
		return this.text.getValue();
	}
	
	public FrameB sendKeysToText(CharSequence... keys) {
		this.text.sendKeys(keys);
		return this;
	}
	
	public boolean isAnyRadiobuttonChecked() {
		List<Radiobutton> radiobuttons = this.findElements(Radiobutton.class, By.name("boolean"));
		for (Radiobutton r : radiobuttons) {
			if (r.isChecked()) return true;
		}
		return false;
	}
	
	public void checkRadiobutton(int i) {
		List<Radiobutton> radiobuttons = this.findElements(Radiobutton.class, By.name("boolean"));
		radiobuttons.get(i).check();
	}
	
}
