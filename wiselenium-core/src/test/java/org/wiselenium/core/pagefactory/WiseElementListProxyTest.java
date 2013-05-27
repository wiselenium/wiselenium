package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.wiselenium.core.element.field.impl.TextImpl;
import org.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseElementListProxyTest extends WiseTestNG<WiseElementListProxyTest> {
	
	@Test(expectedExceptions = IndexOutOfBoundsException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		List<TextImpl> proxy;
		try {
			DummyPage page = initElements(this.getDriver(), DummyPage.class);
			List<WebElement> webElements = page.getRadiobuttons();
			proxy = WiseElementListProxy.getInstance(TextImpl.class, webElements);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.get(0);
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		List<DummyFieldWithoutNoArgConstructor> proxy;
		try {
			DummyPage page = initElements(this.getDriver(), DummyPage.class).and().get();
			List<WebElement> webElements = page.getRadiobuttons();
			proxy = WiseElementListProxy.getInstance(DummyFieldWithoutNoArgConstructor.class,
				webElements);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.get(0);
	}
	
}
