package org.wiselenium.core;

import static org.wiselenium.core.AnnotationUtils.isAnnotationPresent;
import static org.wiselenium.core.ClasspathUtil.findImplementationClass;

import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements and WebPages.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public class WiseDecorator extends DefaultFieldDecorator {
	
	// TODO remove the driver from here and instantiate the locatorfactory at the wisepagefactory
	private WebDriver driver;
	
	
	/**
	 * @param driver The driver used to locate the elements.
	 */
	public WiseDecorator(WebDriver driver) {
		this(new DefaultElementLocatorFactory(driver));
		this.driver = driver;
	}
	
	private WiseDecorator(ElementLocatorFactory factory) {
		super(factory);
	}
	
	private static Enhancer createEnhancerForField(Class<?> implentationClass, WebElement webElement) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implentationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(WiseElementProxy.getInstance(webElement));
		
		return e;
	}
	
	private static Object createInstanceWithEmptyConstructor(Class<?> implentationClass,
		WebElement webElement) {
		Enhancer e = createEnhancerForField(implentationClass, webElement);
		return e.create();
	}
	
	private static Object createInstanceWithWebElementConstructor(Class<?> implentationClass,
		Field field, WebElement webElement) {
		validateClassHasConstructorWithWebElement(field.getType());
		
		Enhancer e = createEnhancerForField(implentationClass, webElement);
		return e.create(new Class[] { WebElement.class }, new Object[] { webElement });
	}
	
	private static boolean shouldDecorateWiseElement(Field field) {
		if (isAnnotationPresent(field.getType(), org.wiselenium.core.Field.class)
			|| isAnnotationPresent(field.getType(), org.wiselenium.core.Container.class)
			|| isAnnotationPresent(field.getType(), org.wiselenium.core.Frame.class)) return true;
		return false;
	}
	
	private static void validateClassHasConstructorWithWebElement(Class<?> clazz) {
		try {
			clazz.getConstructor(WebDriver.class);
		} catch (Exception e) {
			throw new ClassWithNoConstructorWithWebElementException(clazz);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!shouldDecorateWiseElement(field)) return super.decorate(loader, field);
		
		ElementLocator locator = this.factory.createLocator(field);
		if (locator == null) return null;
		
		if (isAnnotationPresent(field.getType(), org.wiselenium.core.Field.class))
			return this.decorateField(field, loader, locator);
		
		return null;
	}
	
	private Object decorateField(Field field, ClassLoader loader, ElementLocator locator) {
		WebElement webElement = this.proxyForLocator(loader, locator);
		
		Class<?> implentationClass = findImplementationClass(field.getType());
		try {
			return WiseDecorator.createInstanceWithWebElementConstructor(implentationClass, field,
				webElement);
		} catch (ClassWithNoConstructorWithWebElementException e) {
			return WiseDecorator.createInstanceWithEmptyConstructor(implentationClass, webElement);
		}
	}
	
}
