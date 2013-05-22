package org.wiselenium.core.pagefactory.dummy;

import org.openqa.selenium.support.FindBy;

@SuppressWarnings("javadoc")
public class DummyPageWithFieldWithoutNoArgConstructor {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private DummyFieldWithoutNoArgConstructor dummy;
	
}
