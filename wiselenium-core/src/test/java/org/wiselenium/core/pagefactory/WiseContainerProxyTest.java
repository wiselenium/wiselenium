package org.wiselenium.core.pagefactory;

import static org.mockito.Mockito.mock;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.container.impl.SelectImpl;
import org.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseContainerProxyTest extends WiseTestNG<WiseContainerProxyTest> {
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		Select proxy;
		try {
			DummyPage page = this.initElements(DummyPage.class);
			WebElement webElement = page.getText();
			proxy = WiseContainerProxy.getInstance(SelectImpl.class, webElement);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.getId();
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		WiseContainerProxy.getInstance(DummyFieldWithoutNoArgConstructor.class,
			mock(WebElement.class));
	}
	
}
