package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.AnnotationUtils.isAnnotationPresent;
import static org.wiselenium.core.pagefactory.ClasspathUtils.findImplementationClass;

import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Class responsible for decorating WebElements into Fields.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WiseFieldDecorator extends DefaultFieldDecorator implements WiseDecoratorChain {
	
	private FieldDecorator nextDecoratorInChain;
	
	
	WiseFieldDecorator(ElementLocatorFactory factory) {
		super(factory);
	}
	
	private static Enhancer createEnhancer(WebElement webElement, Class<?> implentationClass) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implentationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(WiseElementProxy.getInstance(webElement));
		
		return e;
	}
	
	private static Object createInstanceWithEmptyConstructor(WebElement webElement,
		Class<?> implentationClass) {
		Enhancer e = createEnhancer(webElement, implentationClass);
		return e.create();
	}
	
	private static Object createInstanceWithWebElementConstructor(WebElement webElement,
		Class<?> implentationClass) {
		
		try {
			return implentationClass.getConstructor(WebElement.class).newInstance(webElement);
		} catch (Exception e) {
			throw new ClassWithoutConstructorWithWebElementException(e);
		}
	}
	
	private static boolean shouldDecorate(Field field) {
		return isAnnotationPresent(field.getType(), org.wiselenium.core.Field.class);
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!shouldDecorate(field)) return this.nextDecoratorInChain.decorate(loader, field);
		
		ElementLocator locator = this.factory.createLocator(field);
		if (locator == null) return null;
		
		WebElement webElement = this.proxyForLocator(loader, locator);
		
		Class<?> implentationClass = findImplementationClass(field.getType());
		try {
			return createInstanceWithWebElementConstructor(webElement, implentationClass);
		} catch (ClassWithoutConstructorWithWebElementException e) {
			return createInstanceWithEmptyConstructor(webElement, implentationClass);
		}
	}
	
	@Override
	public void setNext(FieldDecorator decorator) {
		this.nextDecoratorInChain = decorator;
	}
	
}
