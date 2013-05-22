package org.wiselenium.core.pagefactory;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;
import static org.wiselenium.core.pagefactory.WiseLocator.findElement;
import static org.wiselenium.core.pagefactory.WiseLocator.findElements;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_RADIOBUTTONS;
import static org.wiselenium.core.pagefactory.dummy.DummyPage.BY_SELECT1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.pagefactory.dummy.DummyPage;

@SuppressWarnings("javadoc")
public class WiseLocatorTest extends TestBase {
	
	private DummyPage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page = initElements(this.driver, DummyPage.class).and().get();
	}
	
	@Test
	public void shouldFindElement() {
		Select select = findElement(Select.class, BY_SELECT1, this.page.getWrappedDriver());
		assertNotNull(select);
		assertNotNull(unwrapWebElement(select));
	}
	
	@SuppressWarnings("null")
	@Test
	public void shouldFindElements() {
		List<Radiobutton> radiobuttons = findElements(Radiobutton.class, BY_RADIOBUTTONS,
			this.page.getWrappedDriver());
		assertTrue(radiobuttons != null && !radiobuttons.isEmpty());
		for (Radiobutton radiobutton : radiobuttons)
			assertNotNull(unwrapWebElement(radiobutton));
	}
	
	@Test
	public void shouldReturnEmptyListWhenElementsAreNotFound() {
		List<Select> selects = findElements(Select.class, By.id("inexistent"),
			this.page.getWrappedDriver());
		assertTrue(selects != null && selects.isEmpty());
	}
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldThrowExceptionWhenElementIsntFound() {
		findElement(Select.class, By.id("inexistent"), this.page.getWrappedDriver());
	}
	
}
