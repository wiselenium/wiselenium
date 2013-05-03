package org.wiselenium.core;

import static org.testng.Assert.assertNotNull;
import static org.wiselenium.core.WisePageFactory.initElements;

import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends TestBase {
	
	@Test
	public void shouldInitElementsInjectingThemThroughConstructor() {
		this.driver
			.get(getAbsoluteFilePath(DummyPageWithoutInheritanceAndWithWebDriverInjectedThoughConstructor.URL));
		
		DummyPageWithoutInheritanceAndWithWebDriverInjectedThoughConstructor page = initElements(
			this.driver, DummyPageWithoutInheritanceAndWithWebDriverInjectedThoughConstructor.class);
		
		assertNotNull(page.getWebDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
	@Test
	public void shouldInitElementsInjectingThemThroughSuperConstructor() {
		this.driver.get(getAbsoluteFilePath(DummyPageWithWebDriverInjectedThoughConstructor.URL));
		
		DummyPageWithWebDriverInjectedThoughConstructor page = initElements(this.driver,
			DummyPageWithWebDriverInjectedThoughConstructor.class);
		
		assertNotNull(page.getWebDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
	@Test
	public void shouldInitElementsInjectingThemWithoutConstructor() {
		this.driver
			.get(getAbsoluteFilePath(DummyPageWithoutInheritanceAndWithEmptyConstructor.URL));
		
		DummyPageWithoutInheritanceAndWithEmptyConstructor page = initElements(this.driver,
			DummyPageWithoutInheritanceAndWithEmptyConstructor.class);
		
		assertNotNull(page.getWebDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
}
