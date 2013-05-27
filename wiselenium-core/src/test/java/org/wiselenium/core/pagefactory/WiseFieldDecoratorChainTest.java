package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.pagefactory.dummy.DummyPage;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseFieldDecoratorChainTest extends WiseTestNG<WiseFieldDecoratorChainTest> {
	
	private DummyPage page;
	private WiseFieldDecoratorChain decorator;
	
	
	@BeforeClass
	public void initDecorator() {
		this.page = initElements(this.getDriver(), DummyPage.class);
		this.decorator = new WiseFieldDecoratorChain(new DefaultElementLocatorFactory(
			this.getDriver()));
	}
	
	@Test
	public void shouldDecorate() {
		Text element = this.decorator.decorate(Text.class, this.page.getText());
		Assert.assertNotNull(element);
	}
	
	@Test
	public void shouldReturnEmptyListOnDecorateElementsWhenItShouldntDecorateAndTheresNoNextDecorator() {
		List<Select> list = this.decorator.decorate(Select.class, this.page.getRadiobuttons());
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void shouldReturnNullOnDecorateElementWhenItShouldntDecorateAndTheresNoNextDecorator() {
		Select element = this.decorator.decorate(Select.class, this.page.getText());
		Assert.assertNull(element);
	}
	
}
