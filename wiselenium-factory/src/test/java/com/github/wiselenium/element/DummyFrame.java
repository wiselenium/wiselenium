package com.github.wiselenium.element;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.factory.annotation.Frame;

@Frame
public interface DummyFrame {
	
	void setTextValue(CharSequence... keys);
	
	String getTextValue();
	
	void clearText();
	
	WebElement getRoot();
	
}
