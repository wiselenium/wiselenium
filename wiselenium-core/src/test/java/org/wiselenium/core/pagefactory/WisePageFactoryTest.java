package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotNull;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends TestBase {
	
	@Test(expectedExceptions = PageCreationException.class)
	public void shouldFailWhileCreatingPage() {
		this.driver.get(getAbsoluteFilePath(DummyPageWithFinalField.URL));
		initElements(this.driver, DummyPageWithFinalField.class);
		System.out.println("ae");
	}
	
	@Test
	public void shouldInitElementsInjectingThemThroughConstructor() {
		this.driver
			.get(getAbsoluteFilePath(DummyPageWithoutInheritanceAndWithWebDriverConstructor.URL));
		
		DummyPageWithoutInheritanceAndWithWebDriverConstructor page = initElements(this.driver,
			DummyPageWithoutInheritanceAndWithWebDriverConstructor.class);
		
		assertNotNull(page.getWrappedDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
	@Test
	public void shouldInitElementsInjectingThemThroughSuperConstructor() {
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
	
}
