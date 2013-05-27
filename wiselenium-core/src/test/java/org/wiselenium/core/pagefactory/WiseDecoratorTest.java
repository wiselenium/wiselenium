package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.WiseDecorator.decorateElement;
import static org.wiselenium.core.pagefactory.WiseDecorator.decorateElements;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_RADIOBUTTONS;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_SELECT1;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.ButtonPage;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseDecoratorTest extends WiseTestNG<WiseDecoratorTest> {
	
	private DummyPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = this.initElements(DummyPage.class).and().get();
	}
	
	@Test
	public void shouldDecorateWebElement() {
		WebElement webElement = this.findElement(WebElement.class, BY_SELECT1);
		Select select = decorateElement(Select.class, webElement);
		assertNotNull(select);
	}
	
	@Test
	public void shouldDecorateWebElementEvenWhenNotFoundAtTheTime() {
		WebElement webElement = this.findElement(WebElement.class, BY_SELECT1);
		this.page.get(ButtonPage.URL);
		Select select = decorateElement(Select.class, webElement);
		assertNotNull(select);
	}
	
	@Test
	public void shouldDecorateWebElements() {
		List<WebElement> webElements = this.findElements(WebElement.class, BY_RADIOBUTTONS);
		List<Radiobutton> radiobuttons = decorateElements(Radiobutton.class, webElements);
		assertTrue(radiobuttons != null && !radiobuttons.isEmpty());
	}
	
	@Test
	public void shouldDecorateWebElementsEvenWhenNotFoundAtTheTime() {
		List<WebElement> webElements = this.findElements(WebElement.class, BY_RADIOBUTTONS);
		this.page.get(ButtonPage.URL);
		List<Radiobutton> radiobuttons = decorateElements(Radiobutton.class, webElements);
		assertNotNull(radiobuttons);
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenDecoratingTypeWithoutNoArgConstructor() {
		decorateElement(DummyFieldWithoutNoArgConstructor.class,
			unwrapWebElement(this.page.getText()));
	}
	
}
