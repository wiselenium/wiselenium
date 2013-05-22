package org.wiselenium.core.pagefactory;

import static org.mockito.Mockito.mock;
import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithWebDriverConstructor;

@SuppressWarnings("javadoc")
public class WisePageProxyTest extends TestBase {
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		this.driver.get(getAbsoluteFilePath(DummyPageWithNoArgConstructor.URL));
		
		DummyPageWithNoArgConstructor page = initElements(this.driver,
			DummyPageWithNoArgConstructor.class);
		
		page.throwIllegalArgumentException();
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		WisePageProxy.getInstance(mock(WebDriver.class), DummyPageWithWebDriverConstructor.class);
	}
	
}
