package com.github.wiselenium.page;

import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.element.DummyField;

@SuppressWarnings("javadoc")
public class DummyPageWithNoArgConstructor {
	
	@FindBy(id = "button")
	private DummyField dummy;
	
	public DummyField getDummyField() {
		return this.dummy;
	}
	
}
