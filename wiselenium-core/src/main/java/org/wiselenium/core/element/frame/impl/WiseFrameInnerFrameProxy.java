package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.getWrappedElement;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.isGetWrappedElement;
import static org.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.exportFields;
import static org.wiselenium.core.util.FrameUtil.getCurrentFramePath;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.pagefactory.WisePageFactory;
import org.wiselenium.core.util.FrameUtil;

/**
 * The wiselenium proxy for frame inner frames.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
final class WiseFrameInnerFrameProxy<E> implements MethodInterceptor {
	
	private final E wrappedElement;
	private final List<String> framePath;
	private boolean elementsInitialized;
	
	
	private WiseFrameInnerFrameProxy(E element) {
		this.wrappedElement = element;
		this.framePath = FrameUtil.getCurrentFramePath();
	}
	
	static <E> E getInstance(E element) {
		return WiseFrameInnerElementUtil.createProxy(element,
			new WiseFrameInnerFrameProxy<E>(element));
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return getWrappedElement(this.wrappedElement);
		
		List<String> currentPath = getCurrentFramePath();
		try {
			this.switchToFrame();
			this.initElements(obj);
			return proxy.invokeSuper(obj, args);
		} finally {
			FrameUtil.switchToFrame(currentPath);
		}
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(WiseThreadLocal.getDriver(), obj);
			exportFields(obj);
			this.elementsInitialized = true;
		}
	}
	
	private void switchToFrame() {
		FrameUtil.switchToFrame(this.framePath);
		WiseThreadLocal.getDriver().switchTo()
			.frame((WebElement) WiseFrameInnerElementUtil.getWrappedElement(this.wrappedElement));
	}
	
}
