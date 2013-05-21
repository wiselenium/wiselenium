package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.container.Select;

@SuppressWarnings("javadoc")
public class DummyPage extends Page<DummyPage> {
	
	public static final By BY_SELECT1 = By.id("select1");
	public static final By BY_RADIOBUTTONS = By.name("sex");
	public static final String URL = "dummy.html";
	
	@FindBy(name = "sex")
	private List<WebElement> radiobuttons;
	private WebElement text;
	private Select select1;
	
	
	public List<WebElement> getRadiobuttons() {
		return this.radiobuttons;
	}
	
	public Select getSelect1() {
		return this.select1;
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
