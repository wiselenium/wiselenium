package org.wiselenium.core.test;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.test.util.TestResourceFinder.getAbsolutePath;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Hyperlink;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class DummyPage extends Page<DummyPage> {
	
	public static final By BY_SELECT1 = By.id("select1");
	public static final By BY_RADIOBUTTONS = By.name("sex");
	public static final String URL = getAbsolutePath("dummy.html");
	
	@FindBy(name = "sex")
	private List<WebElement> radiobuttons;
	private WebElement text;
	private Select select1;
	private Hyperlink link;
	
	
	public Hyperlink getLink() {
		return this.link;
	}
	
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
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
