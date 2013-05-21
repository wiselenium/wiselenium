package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.pagefactory.DummyPage.BY_RADIOBUTTONS;
import static org.wiselenium.core.pagefactory.DummyPage.BY_SELECT1;
import static org.wiselenium.core.pagefactory.WiseLocator.findElement;
import static org.wiselenium.core.pagefactory.WiseLocator.findElements;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Radiobutton;

@SuppressWarnings("javadoc")
public class WiseLocatorTest extends TestBase {
	
	private DummyPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, DummyPage.class).and().get();
	}
	
	@Test
	public void shouldLocateWebElement() {
		Select select = findElement(Select.class, BY_SELECT1, this.page.getWrappedDriver());
		assertNotNull(select);
	}
	
	@Test
	public void shouldLocateWebElements() {
		List<Radiobutton> radiobuttons = findElements(Radiobutton.class, BY_RADIOBUTTONS,
			this.page.getWrappedDriver());
		assertTrue(radiobuttons != null && !radiobuttons.isEmpty());
	}
	
}
