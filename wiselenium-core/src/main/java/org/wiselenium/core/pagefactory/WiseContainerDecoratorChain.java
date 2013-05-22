package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.AnnotationUtils.isAnnotationPresent;
import static org.wiselenium.core.pagefactory.ClasspathUtils.findImplementationClass;

import java.util.List;

import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.testng.collections.Lists;

/**
 * Class responsible for decorating WebElements into Containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class WiseContainerDecoratorChain extends ExtendedSeleniumDecoratorChainTemplate {
	
	WiseContainerDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		Class<? extends E> implentationClass = findImplementationClass(clazz);
		
		Enhancer e = new Enhancer();
		e.setSuperclass(implentationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(WiseContainerProxy.getInstance(webElement));
		
		return (E) e.create();
	}
	
	@Override
	protected <E> List<E> decorateWebElements(Class<E> clazz, List<WebElement> webElements) {
		// FIXME decorateWebElements
		List<E> elements = Lists.newArrayList();
		for (WebElement webElement : webElements)
			elements.add(this.decorateWebElement(clazz, webElement));
		return elements;
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		return isAnnotationPresent(clazz, org.wiselenium.core.Container.class);
	}
	
}
