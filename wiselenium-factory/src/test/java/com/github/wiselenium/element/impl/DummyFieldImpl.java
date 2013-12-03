package com.github.wiselenium.element.impl;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.element.DummyField;
import com.github.wiselenium.factory.annotation.Root;

public class DummyFieldImpl implements DummyField {
	
	@Root
	private WebElement root;
	
	@Override
	public WebElement getRoot() {
		return this.root;
	}
	
}
