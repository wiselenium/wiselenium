package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.AnnotationUtils.isAnnotationPresent;
import static org.wiselenium.core.pagefactory.ClasspathUtils.findImplementationClass;
import static org.wiselenium.core.pagefactory.WisePageFactory.initElements;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements into Containers.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
class WiseContainerDecoratorChain extends ExtendedDefaultSeleniumDecoratorChain {
	
	WiseContainerDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	private static Enhancer createEnhancer(WebElement webElement, Class<?> implentationClass) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implentationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(WiseContainerProxy.getInstance(webElement));
		
		return e;
	}
	
	@SuppressWarnings("unchecked")
	private static <E> E createInstanceWithEmptyConstructor(WebElement webElement,
		Class<E> implentationClass) {
		Enhancer e = createEnhancer(webElement, implentationClass);
		return (E) e.create();
	}
	
	private static <E> E createInstanceWithWebElementConstructor(WebElement webElement,
		Class<E> implentationClass) {
		
		try {
			return implentationClass.getConstructor(WebElement.class).newInstance(webElement);
		} catch (Exception e) {
			throw new ClassWithoutConstructorWithWebElementException(e);
		}
	}
	
	@Override
	protected Object decorateField(ClassLoader loader, Field field, ElementLocator locator) {
		if (this.isDecoratableList(field)) {
			List<WebElement> webElements = this.proxyForListLocator(loader, locator);
			return this
				.decorate((Class<?>) ((ParameterizedType) field.getGenericType())
					.getActualTypeArguments()[0], webElements);
		}
		WebElement webElement = this.proxyForLocator(loader, locator);
		return this.decorate(field.getType(), webElement);
	}
	
	@Override
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		Class<? extends E> implentationClass = findImplementationClass(clazz);
		E instance;
		try {
			instance = createInstanceWithWebElementConstructor(webElement, implentationClass);
			initElements(webElement, instance);
		} catch (ClassWithoutConstructorWithWebElementException e) {
			instance = createInstanceWithEmptyConstructor(webElement, implentationClass);
		}
		
		return instance;
	}
	
	@Override
	protected boolean shouldDecorate(Class<?> clazz) {
		return isAnnotationPresent(clazz, org.wiselenium.core.Container.class);
	}
	
}
