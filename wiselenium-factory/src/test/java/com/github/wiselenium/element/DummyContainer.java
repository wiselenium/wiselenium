package com.github.wiselenium.element;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.factory.annotation.Container;

@Container
public interface DummyContainer {
	
	WebElement getRoot();
	
	List<WebElement> getOptions();
	
}
