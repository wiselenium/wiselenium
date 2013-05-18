package org.wiselenium.core.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.WebElement;
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
		WiseFieldDecoratorChain wiseFieldDecorator = new WiseFieldDecoratorChain(factory);
		WiseContainerDecoratorChain wiseContainerDecorator = new WiseContainerDecoratorChain(factory);
		ExtendedDefaultSeleniumDecoratorChain extendedDefaultFieldDecorator = new ExtendedDefaultSeleniumDecoratorChain(
			factory);
		wiseFieldDecorator.setNext(wiseContainerDecorator);
		wiseContainerDecorator.setNext(extendedDefaultFieldDecorator);
		
		this.decoratorChain = wiseFieldDecorator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decorate(Class<?> clazz, WebElement webElement) {
		return this.decoratorChain.decorate(clazz, webElement);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.decoratorChain.decorate(loader, field);
	}
	
}
