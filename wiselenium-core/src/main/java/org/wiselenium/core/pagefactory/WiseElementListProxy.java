package org.wiselenium.core.pagefactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.testng.collections.Lists;

/**
 * The wiselenium proxy for a list of elements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseElementListProxy<E> implements MethodInterceptor {
	
	private final Class<E> clazz;
	private final List<WebElement> webElements;
	private List<E> elements;
	
	
	private WiseElementListProxy(Class<E> clazz, List<WebElement> webElements) {
		this.clazz = clazz;
		this.webElements = webElements;
	}
	
	@SuppressWarnings("unchecked")
	static <E> List<E> getInstance(Class<E> clazz, List<WebElement> webElements) {
		Enhancer e = new Enhancer();
		e.setInterfaces(new Class[] { List.class });
		e.setCallback(new WiseElementListProxy<E>(clazz, webElements));
		return (List<E>) e.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		try {
			this.initElements();
			return method.invoke(this.elements, args);
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
	}
	
	private synchronized void initElements() {
		if (this.elements == null) {
			this.elements = Lists.newArrayList();
			for (WebElement webElement : this.webElements) {
				E element = WiseDecorator.decorateElement(this.clazz, webElement);
				this.elements.add(element);
			}
		}
	}
	
}
