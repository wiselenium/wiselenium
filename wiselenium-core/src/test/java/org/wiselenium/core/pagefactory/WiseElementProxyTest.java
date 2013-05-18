package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.FileUtils.getAbsoluteFilePath;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;

@SuppressWarnings("javadoc")
public class WiseElementProxyTest extends TestBase {
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		this.driver
			.get(getAbsoluteFilePath(DummyPageWithoutInheritanceAndWithEmptyConstructor.URL));
		
		DummyPageWithoutInheritanceAndWithEmptyConstructor page = initElements(this.driver,
			DummyPageWithoutInheritanceAndWithEmptyConstructor.class);
		
		page.getDummyField().throwIllegalArgumetException();
	}
	
}
