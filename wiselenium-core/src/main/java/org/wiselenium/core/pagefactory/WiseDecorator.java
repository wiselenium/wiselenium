package org.wiselenium.core.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Class responsible for decorating WebElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class WiseDecorator implements FieldDecorator {
	
	private final WiseDecoratorChain decoratorChain;
	
	
	WiseDecorator(ElementLocatorFactory factory) {
		this.decoratorChain = new WiseFieldDecorator(factory);
		this.decoratorChain.setNext(new DefaultFieldDecorator(factory));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.decoratorChain.decorate(loader, field);
	}
	
}
