package org.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Extended default decorator for use with WisePageFactory. Will decorate 1) all of the WebElement
 * fields and 2) List<WebElement> fields and 3) Types annotated with Field, Container or Frame that
 * have @FindBy or @FindBys annotation with a proxy that locates the elements using the passed in
 * ElementLocatorFactory.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
class DefaultExtendedSeleniumDecoratorChain extends ExtendedSeleniumDecoratorChainTemplate {
	
	DefaultExtendedSeleniumDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		return (E) webElement;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <E> List<E> decorateWebElements(Class<E> clazz, List<WebElement> webElements) {
		return (List<E>) webElements;
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		if (WebElement.class.isAssignableFrom(clazz)) return true;
		return false;
	}
	
}
