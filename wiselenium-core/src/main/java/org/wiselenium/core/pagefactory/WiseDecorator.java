package org.wiselenium.core.pagefactory;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WiseDecorator implements ExtendedSeleniumDecorator {
	
	private final ExtendedSeleniumDecoratorChain decoratorChain;
	
	
	/**
	 * @param factory The factory of the locator of the elements.
	 */
	public WiseDecorator(ElementLocatorFactory factory) {
		ExtendedSeleniumDecoratorChain extendedDefaultSeleniumDecorator = new DefaultExtendedSeleniumDecoratorChain(
			factory);
		ExtendedSeleniumDecoratorChain wiseContainerDecorator = new WiseContainerDecoratorChain(
			factory).setNext(extendedDefaultSeleniumDecorator);
		ExtendedSeleniumDecoratorChain wiseFieldDecorator = new WiseFieldDecoratorChain(factory)
			.setNext(wiseContainerDecorator);
		
		this.decoratorChain = wiseFieldDecorator;
	}
	
	/**
	 * Decorates a webElement.
	 * 
	 * @param <E> The type of the decorated element.
	 * @param clazz The class of the decorated element. Must be either WebElement or a type
	 * annotated with Field, Container or Frame. Will lookup for its implementation class following
	 * {@link org.wiselenium.core.ClasspathUtils#findImplementationClass(Class)} rules.
	 * @param webElement The webElement that will be decorated.
	 * @return The webElement decorated.
	 * @since 0.0.1
	 */
	public static <E> E decorateElement(Class<E> clazz, WebElement webElement) {
		WiseDecorator decorator = new WiseDecorator(new DefaultElementLocatorFactory(webElement));
		return decorator.decorate(clazz, webElement);
	}
	
	/**
	 * Decorates a list of webElements.
	 * 
	 * @param <E> The type of the decorated elements.
	 * @param clazz The class of the decorated elements. Must be either WebElement or a type
	 * annotated with Field, Container or Frame. Will lookup for its implementation class following
	 * {@link org.wiselenium.core.ClasspathUtils#findImplementationClass(Class)} rules.
	 * @param webElements The webElements that will be decorated.
	 * @return The webElements decorated.
	 * @since 0.0.1
	 */
	public static <E> List<E> decorateElements(Class<E> clazz, List<WebElement> webElements) {
		return WiseElementListProxy.getInstance(clazz, webElements);
	}
	
	@Override
	public <E> List<E> decorate(Class<E> clazz, List<WebElement> webElements) {
		return this.decoratorChain.decorate(clazz, webElements);
	}
	
	@Override
	public <E> E decorate(Class<E> clazz, WebElement webElement) {
		return this.decoratorChain.decorate(clazz, webElement);
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.decoratorChain.decorate(loader, field);
	}
	
}
