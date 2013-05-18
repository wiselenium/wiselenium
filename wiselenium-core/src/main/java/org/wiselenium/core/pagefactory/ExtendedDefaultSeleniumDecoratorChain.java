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
class ExtendedDefaultSeleniumDecoratorChain extends DefaultFieldDecorator implements
	ExtendedSeleniumDecoratorChain {
	
	private ExtendedSeleniumDecorator nextDecoratorInChain;
	
	
	ExtendedDefaultSeleniumDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	public Object decorate(Class<?> clazz, WebElement webElement) {
		if (!this.shouldDecorate(clazz)) return this.callNextDecorator(clazz, webElement);
		return this.decorateWebElement(clazz, webElement);
	}
	
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!this.shouldDecorate(field)) return this.callNextDecorator(loader, field);
		
		ElementLocator locator = this.factory.createLocator(field);
		if (locator == null) return null;
		
		return this.decorateField(loader, field, locator);
	}
	
	@Override
	public ExtendedSeleniumDecoratorChain setNext(ExtendedSeleniumDecorator decorator) {
		this.nextDecoratorInChain = decorator;
		return this;
	}
	
	protected Object callNextDecorator(Class<?> clazz, WebElement webElement) {
		if (this.nextDecoratorInChain != null)
			return this.nextDecoratorInChain.decorate(clazz, webElement);
		return null;
	}
	
	protected Object callNextDecorator(ClassLoader loader, Field field) {
		if (this.nextDecoratorInChain != null)
			return this.nextDecoratorInChain.decorate(loader, field);
		return null;
	}
	
	protected Object decorateField(ClassLoader loader, Field field, ElementLocator locator) {
		if (WebElement.class.isAssignableFrom(field.getType()))
			return this.proxyForLocator(loader, locator);
		else if (List.class.isAssignableFrom(field.getType()))
			return this.proxyForListLocator(loader, locator);
		else return null;
	}
	
	protected Object decorateWebElement(@SuppressWarnings("unused") Class<?> clazz,
		WebElement webElement) {
		return webElement;
	}
	
	protected boolean isDecoratableList(Field field) {
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
	
	protected boolean shouldDecorate(Class<?> clazz) {
		// TODO implement the decoratable list verification too
		if (WebElement.class.isAssignableFrom(clazz)) return true;
		return false;
	}
	
	protected boolean shouldDecorate(Field field) {
		if (this.shouldDecorate(field.getType()) || this.isDecoratableList(field)) return true;
		return false;
	}
	
}
