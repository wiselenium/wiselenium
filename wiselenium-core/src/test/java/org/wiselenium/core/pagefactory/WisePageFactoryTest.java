package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Option;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends TestBase {
	
	@Test(expectedExceptions = PageElementsInitializationException.class)
	public void shouldFailWhileCreatingPage() {
		this.driver.get(getAbsoluteFilePath(DummyPageWithFinalField.URL));
		initElements(this.driver, DummyPageWithFinalField.class);
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldInitElementsInjectingThemThroughConstructor() {
		// TODO review
		this.driver
			.get(getAbsoluteFilePath(DummyPageWithoutInheritanceAndWithWebDriverConstructor.URL));
		
		DummyPageWithoutInheritanceAndWithWebDriverConstructor page = initElements(this.driver,
			DummyPageWithoutInheritanceAndWithWebDriverConstructor.class);
		
		assertNotNull(page.getWrappedDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldInitElementsInjectingThemThroughSuperConstructor() {
		// TODO review
		DummyPageWithWebDriverConstructor page = initElements(this.driver,
			DummyPageWithWebDriverConstructor.class);
		
		page.get();
		assertNotNull(page.getWrappedDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
	@Test
	public void shouldInitElementsInjectingThemWithEmptyConstructor() {
		this.driver
			.get(getAbsoluteFilePath(DummyPageWithoutInheritanceAndWithEmptyConstructor.URL));
		
		DummyPageWithoutInheritanceAndWithEmptyConstructor page = initElements(this.driver,
			DummyPageWithoutInheritanceAndWithEmptyConstructor.class);
		
		assertNotNull(unwrapWebDriver(page));
		assertNotNull(page.getWrappedDriver());
		assertNotNull(unwrapWebElement(page.getDummyField()));
		assertNotNull(page.getDummyField().getWrappedElement());
	}
	
	@Test
	public void shouldInitElementsOfContainerLazily() {
		DummyPage page = initElements(this.driver, DummyPage.class).and().get();
		
		Select select1 = page.getSelect1();
		assertNotNull(select1);
		
		List<Option> options = select1.getOptions();
		assertTrue(options != null && !options.isEmpty());
		if (options != null) for (Option option : options)
			assertNotNull(option);
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldInitElementsOfInstance() {
		// TODO review
		DummyPageWithWebDriverConstructor page = new DummyPageWithWebDriverConstructor(this.driver);
		initElements(this.driver, page);
		assertNotNull(page.getDummyElementWebElement());
	}
	
	@Test
	public void shouldInitWebElements() {
		DummyPage page = initElements(this.driver, DummyPage.class).and().get();
		WebElement text = page.getText();
		assertNotNull(text);
		List<WebElement> sexRadiobuttons = page.getRadiobuttons();
		assertFalse(sexRadiobuttons.isEmpty());
	}
	
}
