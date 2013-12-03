package com.github.wiselenium.element.impl;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.element.DummyContainer;
import com.github.wiselenium.factory.annotation.Root;

public class DummyContainerImpl implements DummyContainer {
	
	@Root
	private WebElement root;
	
	@FindBy(tagName="option")
	private List<WebElement> options;
	
	@Override
	public WebElement getRoot() {
		return this.root;
	}
	
	@Override
	public List<WebElement> getOptions() {
		return this.options;
	}
	
}
