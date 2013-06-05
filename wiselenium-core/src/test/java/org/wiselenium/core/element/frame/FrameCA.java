package org.wiselenium.core.element.frame;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.frame.impl.BasicFrame;

@SuppressWarnings("javadoc")
public class FrameCA extends BasicFrame<FrameCA> {
	
	private Text text;
	
	@FindBy(id = "text")
	private WebElement webElement;
	
	
	public String getTextValue() {
		return this.text.getValue();
	}
	
	public WebElement getWebElement() {
		return this.exportInnerElement(this.webElement);
	}
	
	public FrameCA sendKeysToText(CharSequence... keys) {
		this.text.sendKeys(keys);
		return this;
	}
	
}
