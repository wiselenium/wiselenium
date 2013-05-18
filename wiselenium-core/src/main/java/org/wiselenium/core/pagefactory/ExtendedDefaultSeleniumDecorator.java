package org.wiselenium.core.pagefactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Extended default decorator for use with WisePageFactory. Will decorate 1) all of the WebElement
 * fields and 2) List<WebElement> fields and 3) Types annotated with Field, Container or Frame that
 * have @FindBy or @FindBys annotation with a proxy that locates the elements using the passed in
 * ElementLocatorFactory.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class ExtendedDefaultSeleniumDecorator extends DefaultFieldDecorator implements
	DecoratorChain {
	
	private ExtendedSeleniumDecorator nextDecoratorInChain;
	
	
	/**
	 * @param factory The factory of the locator of the elements.
	 */
	public ExtendedDefaultSeleniumDecorator(ElementLocatorFactory factory) {
		super(factory);
	}
	
	private static boolean isDecoratableList(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) return false;
		
		// Type erasure in Java isn't complete. Attempt to discover the generic type of the list.
		Type genericType = field.getGenericType();
		if (!(genericType instanceof ParameterizedType)) return false;
		
		Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
		
		if (!WebElement.class.equals(listType)) return false;
		
		if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null)
			return false;
		
		return true;
	}
	
	private static boolean shouldDecorate(Class<?> clazz) {
		// TODO implement the decoratable list verification too
		if (WebElement.class.isAssignableFrom(clazz)) return true;
		return false;
	}
	
	private static boolean shouldDecorate(Field field) {
		if (shouldDecorate(field.getType()) || isDecoratableList(field)) return true;
		return false;
	}
	
	@Override
	public Object decorate(Class<?> clazz, WebElement webElement) {
		if (!shouldDecorate(clazz)) return this.callNextDecorator(clazz, webElement);
		return webElement;
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!shouldDecorate(field)) return this.callNextDecorator(loader, field);
		
		ElementLocator locator = this.factory.createLocator(field);
		if (locator == null) return null;
		
		if (WebElement.class.isAssignableFrom(field.getType()))
			return this.proxyForLocator(loader, locator);
		else if (List.class.isAssignableFrom(field.getType()))
			return this.proxyForListLocator(loader, locator);
		else return null;
	}
	
	@Override
	public DecoratorChain setNext(ExtendedSeleniumDecorator decorator) {
		this.nextDecoratorInChain = decorator;
		return this;
	}
	
	private Object callNextDecorator(Class<?> clazz, WebElement webElement) {
		if (this.nextDecoratorInChain != null)
			return this.nextDecoratorInChain.decorate(clazz, webElement);
		return null;
	}
	
	private Object callNextDecorator(ClassLoader loader, Field field) {
		if (this.nextDecoratorInChain != null)
			return this.nextDecoratorInChain.decorate(loader, field);
		return null;
	}
	
}
