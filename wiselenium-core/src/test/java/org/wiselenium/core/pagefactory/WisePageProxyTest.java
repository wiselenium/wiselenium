package org.wiselenium.core.pagefactory;

import static org.mockito.Mockito.mock;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPageWithWebDriverConstructor;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WisePageProxyTest extends WiseTestNG<WisePageProxyTest> {
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		this.getDriver().get(DummyPageWithNoArgConstructor.URL);
		
		DummyPageWithNoArgConstructor page = initElements(this.getDriver(),
			DummyPageWithNoArgConstructor.class);
		
		page.throwIllegalArgumentException();
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		WisePageProxy.getInstance(mock(WebDriver.class), DummyPageWithWebDriverConstructor.class);
	}
	
}
