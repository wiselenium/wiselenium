package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings("javadoc")
public class DummyPage extends Page<DummyPage> {
	
	public static final String URL = "dummy.html";
	
	@FindBy(name = "sex")
	private List<WebElement> sexRadiobuttons;
	private WebElement text;
	
	
	public List<WebElement> getSexRadiobuttons() {
		return this.sexRadiobuttons;
	}
	
	public WebElement getText() {
		return this.text;
	}
	
	@Override
	protected void isLoaded() {
		assertTrue(this.getWrappedDriver().getCurrentUrl().endsWith(URL));
	}
	
	@Override
	protected void load() {
		this.get(getAbsoluteFilePath(URL));
	}
	
}
