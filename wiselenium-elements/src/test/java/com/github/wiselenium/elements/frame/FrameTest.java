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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.wiselenium.TestBase;
import com.github.wiselenium.elements.frame.page.FramePage;
import com.github.wiselenium.factory.WisePageFactory;

@SuppressWarnings("javadoc")
public class FrameTest extends TestBase {
	
	FramePage page;
	
	@BeforeMethod
	public void initPage() {
		this.page = WisePageFactory.initElements(this.driver, FramePage.class);
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldUseFrameTransparently() {
		String keys = "testFrame";
		FrameA frame_a = this.page.getFrame_a();
		FrameB frame_b = this.page.getFrame_b();
		FrameC frame_c = this.page.getFrame_c();
		
		frame_c.sendKeysToFrameCBAText(keys);
		assertEquals(frame_c.getFrameCBATextValue(), keys);
		
		frame_a.sendKeysToText(keys);
		assertEquals(frame_a.getTextValue(), keys);
		
		assertFalse(frame_b.isAnyRadiobuttonChecked());
		frame_b.checkRadiobutton(1);
		assertTrue(frame_b.isAnyRadiobuttonChecked());
		
		assertEquals(frame_c.sendKeysToFrameCAText(keys).and().getFrameCATextValue(), keys);
		assertEquals(frame_c.sendKeysToFrameCBBText(keys).and().getFrameCBBTextValue(), keys);
		assertEquals(frame_b.sendKeysToText(keys).and().getTextValue(), keys);
	}
	
}
