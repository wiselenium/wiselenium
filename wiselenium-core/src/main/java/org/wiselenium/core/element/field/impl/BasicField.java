package org.wiselenium.core.element.field.impl;

import static org.wiselenium.core.WiseUnwrapper.unwrapWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.wiselenium.core.WiseException;
import org.wiselenium.core.WiseThreadLocal;
import org.wiselenium.core.element.BasicElement;
import org.wiselenium.core.element.field.Field;

/**
 * Basic implementation of a common Field. <br/>
 * Should be extended to reflect your own field methods.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The field type.
 * @since 0.1.0
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
		WebDriver driver = WiseThreadLocal.getDriver();
		if (driver == null)
			throw new WiseException(
				"The driver must be set on the WiseThreadLocal as a pre-condition for the doubleClick() method");
		Action action = new Actions(driver).doubleClick(unwrapWebElement(this)).build();
		action.perform();
		return (T) this;
	}
	
}
