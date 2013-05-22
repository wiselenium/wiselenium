package org.wiselenium.core.pagefactory.dummy;

import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.field.Button;

@SuppressWarnings("javadoc")
public class DummyPageWithFinalField {
	
	public static final String URL = "button.html";
	
	@FindBy(id = "button")
	private static final Button dummy;
	
	static {
		dummy = new Button() {
			
			@Override
			public Button and() {
				return null;
			}
			
			@Override
			public Button click() {
				return null;
			}
			
			@Override
			public Button doubleClick() {
				return null;
			}
			
			@Override
			public String getAttribute(String name) {
				return null;
			}
			
			@Override
			public String getCssValue(String propertyName) {
				return null;
			}
			
			@Override
			public String getId() {
				return null;
			}
			
			@Override
			public String getStyleClass() {
				return null;
			}
			
			@Override
			public String getTitle() {
				return null;
			}
			
			@Override
			public String getType() {
				return null;
			}
			
			@Override
			public String getValue() {
				return null;
			}
			
			@Override
			public boolean isDisplayed() {
				return false;
			}
			
			@Override
			public boolean isEnabled() {
				return false;
			}
		};
	}
	
}
