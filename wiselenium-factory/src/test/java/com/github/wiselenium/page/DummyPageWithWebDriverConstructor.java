package com.github.wiselenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.element.DummyField;

@SuppressWarnings("javadoc")
public class DummyPageWithWebDriverConstructor {
	
	private final WebDriver driver;
	
	@FindBy(id = "button")
	private DummyField dummy;
	
	public DummyPageWithWebDriverConstructor(WebDriver driver) {
		this.driver = driver;
	}
	
	public DummyField getDummyField() {
		return this.dummy;
	}
	
}
