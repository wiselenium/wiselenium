package org.wiselenium.core.element.frame.impl;

import static org.wiselenium.core.util.FrameUtil.getCurrentFramePath;
import static org.wiselenium.core.util.FrameUtil.switchToFrame;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.util.FrameUtil;

/**
 * The wiselenium proxy for frame inner webElements.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameInnerWebElementProxy implements MethodInterceptor {
	
	private final WebElement wrappedElement;
	private final List<String> framePath;
	
	
	private WiseFrameInnerWebElementProxy(WebElement element) {
		this.wrappedElement = element;
		this.framePath = FrameUtil.getCurrentFramePath();
	}
	
	static WebElement getInstance(WebElement element) {
		Enhancer e = new Enhancer();
		e.setInterfaces(element.getClass().getInterfaces());
		e.setCallback(new WiseFrameInnerWebElementProxy(element));
		
		return (WebElement) e.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		List<String> currentPath = getCurrentFramePath();
		try {
			switchToFrame(this.framePath);
			return method.invoke(this.wrappedElement, args);
		} finally {
			switchToFrame(currentPath);
		}
	}
	
}
