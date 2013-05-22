package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.util.List;

import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wiselenium.core.TestBase;
import org.wiselenium.core.element.container.Select;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.pagefactory.dummy.DummyPage;

@SuppressWarnings("javadoc")
public class WiseContainerDecoratorChainTest extends TestBase {
	
	private DummyPage page;
	private WiseContainerDecoratorChain decorator;
	
	
	@BeforeClass
	public void initDecorator() {
		this.page = initElements(this.driver, DummyPage.class);
		this.decorator = new WiseContainerDecoratorChain(new DefaultElementLocatorFactory(
			this.driver));
	}
	
	@Test
	public void shouldDecorate() {
		Select element = this.decorator.decorate(Select.class, this.page.getText());
		Assert.assertNotNull(element);
	}
	
	@Test
	public void shouldReturnEmptyListOnDecorateElementsWhenItShouldntDecorateAndTheresNoNextDecorator() {
		List<Text> list = this.decorator.decorate(Text.class, this.page.getRadiobuttons());
		Assert.assertTrue(list.isEmpty());
	}
	
	@Test
	public void shouldReturnNullOnDecorateElementWhenItShouldntDecorateAndTheresNoNextDecorator() {
		Text element = this.decorator.decorate(Text.class, this.page.getText());
		Assert.assertNull(element);
	}
	
}
