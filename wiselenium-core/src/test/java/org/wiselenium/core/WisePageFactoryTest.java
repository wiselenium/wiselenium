package org.wiselenium.core;

import static org.testng.Assert.assertNotNull;
import static org.wiselenium.core.WisePageFactory.initElements;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebDriver;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends TestBase {
	
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
		this.driver.get(getAbsoluteFilePath(DummyPageWithWebDriverConstructor.URL));
		
		DummyPageWithWebDriverConstructor page = initElements(this.driver,
			DummyPageWithWebDriverConstructor.class);
		
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
