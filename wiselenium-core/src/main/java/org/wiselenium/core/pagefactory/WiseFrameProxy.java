package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.FrameUtils.getCurrentFramePath;
import static org.wiselenium.core.pagefactory.WiseElementProxyUtils.isGetWrappedElement;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.wiselenium.core.FrameUtils;
import org.wiselenium.core.WiseThreadLocal;

/**
 * The wiselenium proxy for frames.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseFrameProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	private List<String> parentFramePath;
	private boolean elementsInitialized;
	
	
	private WiseFrameProxy(WebElement webElement) {
		this.wrappedElement = webElement;
	}
	
	@SuppressWarnings("unchecked")
	static <E> E getInstance(Class<E> implementationClass, WebElement webElement) {
		Enhancer e = new Enhancer();
		e.setSuperclass(implementationClass);
		e.setInterfaces(new Class[] { WrapsElement.class });
		e.setCallback(new WiseFrameProxy(webElement));
		
		try {
			return (E) e.create();
		} catch (IllegalArgumentException ex) {
			throw new ClassWithoutNoArgConstructorException(implementationClass, ex);
		}
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return this.wrappedElement;
		
		List<String> currentPath = getCurrentFramePath();
		try {
			this.switchToFrame();
			this.initElements(obj);
			return proxy.invokeSuper(obj, args);
		} finally {
			FrameUtils.switchToFrame(currentPath);
		}
	}
	
	private synchronized List<String> getParentFramePath() {
		if (this.parentFramePath == null) this.parentFramePath = FrameUtils.getCurrentFramePath();
		return this.parentFramePath;
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(WiseThreadLocal.getDriver(), obj);
			this.elementsInitialized = true;
		}
	}
	
	private synchronized void switchToFrame() {
		FrameUtils.switchToFrame(this.getParentFramePath());
		WiseThreadLocal.getDriver().switchTo().frame(this.wrappedElement);
	}
	
}
