package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.WiseDecorator.decorateElement;
import static org.wiselenium.core.pagefactory.WiseDecorator.decorateElements;
import static org.wiselenium.core.pagefactory.WiseLocator.findElement;
import static org.wiselenium.core.pagefactory.WiseLocator.findElements;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_RADIOBUTTONS;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_SELECT1;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.ButtonPage;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPage;

@SuppressWarnings("javadoc")
public class WiseDecoratorTest extends TestBase {
	
	private DummyPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, DummyPage.class).and().get();
	}
	
	@Test
	public void shouldDecorateWebElement() {
		WebElement webElement = findElement(WebElement.class, BY_SELECT1,
			this.page.getWrappedDriver());
		Select select = decorateElement(Select.class, webElement);
		assertNotNull(select);
	}
	
	@Test
	public void shouldDecorateWebElementEvenWhenNotFoundAtTheTime() {
		WebElement webElement = findElement(WebElement.class, BY_SELECT1,
			this.page.getWrappedDriver());
		this.page.get(getAbsoluteFilePath(ButtonPage.URL));
		Select select = decorateElement(Select.class, webElement);
		assertNotNull(select);
	}
	
	@Test
	public void shouldDecorateWebElements() {
		List<WebElement> webElements = findElements(WebElement.class, BY_RADIOBUTTONS,
			this.page.getWrappedDriver());
		List<Radiobutton> radiobuttons = decorateElements(Radiobutton.class, webElements);
		assertTrue(radiobuttons != null && !radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldDecorateWebElementsEvenWhenNotFoundAtTheTime() {
		List<WebElement> webElements = findElements(WebElement.class, BY_RADIOBUTTONS,
			this.page.getWrappedDriver());
		this.page.get(getAbsoluteFilePath(ButtonPage.URL));
		List<Radiobutton> radiobuttons = decorateElements(Radiobutton.class, webElements);
		assertNotNull(radiobuttons);
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenDecoratingTypeWithoutNoArgConstructor() {
		decorateElement(DummyFieldWithoutNoArgConstructor.class,
			unwrapWebElement(this.page.getText()));
	}
	
}
