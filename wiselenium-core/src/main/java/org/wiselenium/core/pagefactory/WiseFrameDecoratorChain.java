package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.util.AnnotationUtil.isAnnotationPresent;
import static org.wiselenium.core.util.ClasspathUtil.findImplementationClass;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements into Containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class WiseFrameDecoratorChain extends ExtendedSeleniumDecoratorChainTemplate {
	
	WiseFrameDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		Class<? extends E> implementationClass = findImplementationClass(clazz);
		return WiseFrameProxy.getInstance(implementationClass, webElement);
	}
	
	@Override
	protected <E> List<E> decorateWebElements(Class<E> clazz, List<WebElement> webElements) {
		return WiseElementListProxy.getInstance(clazz, webElements, this.factory);
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		return isAnnotationPresent(clazz, org.wiselenium.core.annotation.Frame.class);
	}
	
}
