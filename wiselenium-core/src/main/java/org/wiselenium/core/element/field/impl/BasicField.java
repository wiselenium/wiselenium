package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.element.BasicElement;
import org.wiselenium.core.element.field.Field;

/**
 * Basic implementation of a common Field. <br/>
 * Should be extended to reflect your own field methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The field type.
 * @since 0.0.1
 */
public class BasicField<T extends Field<T>> extends BasicElement<T> implements Field<T> {
	
	@SuppressWarnings("unchecked")
	@Override
	public T click() {
		unwrapWebElement(this).click();
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T doubleClick() {
		Action action = new Actions(WiseThreadLocal.getDriver())
			.doubleClick(unwrapWebElement(this)).build();
		action.perform();
		return (T) this;
	}
	
}
