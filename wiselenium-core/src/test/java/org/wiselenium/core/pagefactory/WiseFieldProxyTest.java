package org.wiselenium.core.pagefactory;

import static org.mockito.Mockito.mock;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.field.impl.TextImpl;
import org.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseFieldProxyTest extends WiseTestNG<WiseFieldProxyTest> {
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		Text proxy;
		try {
			DummyPage page = initElements(this.getDriver(), DummyPage.class);
			WebElement webElement = page.getText();
			proxy = WiseFieldProxy.getInstance(TextImpl.class, webElement);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.getId();
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		WiseFieldProxy.getInstance(DummyFieldWithoutNoArgConstructor.class, mock(WebElement.class));
	}
	
}
