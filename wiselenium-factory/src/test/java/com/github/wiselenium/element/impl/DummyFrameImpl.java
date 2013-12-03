package com.github.wiselenium.element.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.element.DummyFrame;
import com.github.wiselenium.factory.annotation.Root;

public class DummyFrameImpl implements DummyFrame {
	
	@Root
	private WebElement root;
	
	@FindBy(id = "text")
	private WebElement text;
	
	@Override
	public void setTextValue(CharSequence... keys) {
		this.clearText();
		this.text.sendKeys(keys);
	}
	
	@Override
	public String getTextValue() {
		return this.text.getAttribute("value");
	}
	
	@Override
	public WebElement getRoot() {
		return this.root;
	}
	
	@Override
	public void clearText() {
		this.text.clear();
	}
	
}
