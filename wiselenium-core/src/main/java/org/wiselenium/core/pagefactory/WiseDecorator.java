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
		WiseFieldDecorator wiseFieldDecorator = new WiseFieldDecorator(factory);
		WiseContainerDecorator wiseContainerDecorator = new WiseContainerDecorator(factory);
		DefaultFieldDecorator defaultFieldDecorator = new DefaultFieldDecorator(factory);
		wiseFieldDecorator.setNext(wiseContainerDecorator);
		wiseContainerDecorator.setNext(defaultFieldDecorator);
		
		this.decoratorChain = wiseFieldDecorator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		return this.decoratorChain.decorate(loader, field);
	}
	
}
