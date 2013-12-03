package com.github.wiselenium.element;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.factory.annotation.Field;

@Field
public interface DummyField {
	
	WebElement getRoot();
	
}
