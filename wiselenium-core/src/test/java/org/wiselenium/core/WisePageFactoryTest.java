package org.wiselenium.core;

import static org.testng.Assert.assertNotNull;
import static org.wiselenium.core.DummyPageWithWebDriverInjectedThoughConstructor.URL;
import static org.wiselenium.core.WisePageFactory.initElements;

import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class WisePageFactoryTest extends TestBase {
	
	@Test
	public void shouldInitElementsInjectingThemThroughConstructor() {
		this.driver.get(getAbsoluteFilePath(URL));
		
		DummyPageWithWebDriverInjectedThoughConstructor page = initElements(this.driver,
			DummyPageWithWebDriverInjectedThoughConstructor.class);
		
		assertNotNull(page.getPageWebDriver());
		assertNotNull(page.getDummyElementWebElement());
	}
	
}
