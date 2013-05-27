package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.ClasspathUtils.findImplementationClass;
import static org.wiselenium.core.pagefactory.AnnotationUtils.isAnnotationPresent;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements into Fields.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class WiseFieldDecoratorChain extends ExtendedSeleniumDecoratorChainTemplate {
	
	WiseFieldDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		Class<? extends E> implementationClass = findImplementationClass(clazz);
		return WiseFieldProxy.getInstance(implementationClass, webElement);
	}
	
	@Override
	protected <E> List<E> decorateWebElements(Class<E> clazz, List<WebElement> webElements) {
		return WiseElementListProxy.getInstance(clazz, webElements);
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		return isAnnotationPresent(clazz, org.wiselenium.core.Field.class);
	}
	
}
