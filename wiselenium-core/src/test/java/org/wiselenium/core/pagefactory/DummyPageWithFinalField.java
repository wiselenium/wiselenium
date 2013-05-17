package org.wiselenium.core.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.DummyFieldWithEmptyConstructor;

@SuppressWarnings("javadoc")
public class DummyPageWithFinalField {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private static final DummyFieldWithEmptyConstructor dummy;
	
	static {
		dummy = new DummyFieldWithEmptyConstructor() {
			
			@Override
			public WebElement getWrappedElement() {
				return null;
			}
		};
	}
	
}
