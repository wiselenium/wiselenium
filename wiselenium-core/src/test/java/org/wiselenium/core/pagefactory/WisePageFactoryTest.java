package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Option;
import org.wiselenium.core.element.field.TextPage;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithFinalField;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithWebDriverConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithoutProperConstructor;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends WiseTestNG<WisePageFactoryTest> {
	
	@Test
	public void shouldCreatePageWithNoArgConstructorAndInitElements() {
		this.getDriver().get(DummyPageWithNoArgConstructor.URL);
		
		DummyPageWithNoArgConstructor page = initElements(this.getDriver(),
			DummyPageWithNoArgConstructor.class);
		
		assertNotNull(unwrapWebDriver(page));
		assertNotNull(page.getWrappedDriver());
		assertNotNull(unwrapWebElement(page.getDummyField()));
	}
	
	@Test
	public void shouldCreatePageWithWebDriverConstructorAndInitElements() {
		DummyPageWithWebDriverConstructor page = initElements(this.getDriver(),
			DummyPageWithWebDriverConstructor.class).and().get();
		
		assertNotNull(unwrapWebDriver(page));
		assertNotNull(page.getWrappedDriver());
		assertNotNull(unwrapWebElement(page.getDummyElement()));
	}
	
	@Test
	public void shouldInitElementsLazily() {
		this.getDriver().get(TextPage.URL);
		DummyPage page = initElements(this.getDriver(), DummyPage.class).and().get();
		
		Select select1 = page.getSelect1();
		assertNotNull(select1);
		
		List<Option> options = select1.getOptions();
		assertTrue(options != null && !options.isEmpty());
		if (options != null) for (Option option : options)
			assertNotNull(option);
	}
	
	@Test
	public void shouldInitElementsOfInstance() {
		DummyPageWithWebDriverConstructor page = new DummyPageWithWebDriverConstructor(
			this.getDriver());
		initElements(this.getDriver(), page);
		assertNotNull(page.getDummyElement());
		assertNotNull(unwrapWebElement(page.getDummyElement()));
	}
	
	@Test
	public void shouldInitWebElements() {
		DummyPage page = initElements(this.getDriver(), DummyPage.class).and().get();
		WebElement text = page.getText();
		assertNotNull(text);
		List<WebElement> sexRadiobuttons = page.getRadiobuttons();
		assertFalse(sexRadiobuttons.isEmpty());
	}
	
	@Test(expectedExceptions = PageElementsInitializationException.class)
	public void shouldThrowExceptionWhileCreatingPageWithFinalField() {
		this.getDriver().get(DummyPageWithFinalField.URL);
		initElements(this.getDriver(), DummyPageWithFinalField.class);
	}
	
	@Test(expectedExceptions = PageInstantiationException.class)
	public void shouldThrowExceptionWhileInstantiatingPageWithoutProperConstructor() {
		initElements(this.getDriver(), DummyPageWithoutProperConstructor.class);
	}
	
}
