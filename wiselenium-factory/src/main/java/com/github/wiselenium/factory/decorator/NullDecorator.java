package com.github.wiselenium.factory.decorator;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.google.common.collect.Lists;

/**
 * Decorator that does nothing.
 *
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public class NullDecorator extends ElementDecoratorChainTemplate {
	
	public NullDecorator(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	protected <E> E decorateElement(Class<E> clazz, WebElement webElement) {
		return null;
	}
	
	@Override
	protected <E> List<E> decorateElements(Class<E> clazz, List<WebElement> webElements) {
		return Lists.newArrayList();
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		return true;
	}
	
}
