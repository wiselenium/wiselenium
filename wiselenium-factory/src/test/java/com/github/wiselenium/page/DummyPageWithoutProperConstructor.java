package com.github.wiselenium.page;

import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.element.DummyField;

@SuppressWarnings("javadoc")
public class DummyPageWithoutProperConstructor {
	
	private final Object object;
	
	@FindBy(id = "button")
	private DummyField dummy;
	
	public DummyPageWithoutProperConstructor(Object object) {
		this.object = object;
	}
	
	public DummyField getDummyField() {
		return this.dummy;
	}
	
}
