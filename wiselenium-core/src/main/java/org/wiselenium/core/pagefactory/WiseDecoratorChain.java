package org.wiselenium.core.pagefactory;

import org.openqa.selenium.support.pagefactory.FieldDecorator;

/**
 * Allows the decorators to be used in a chain of responsibility.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
public interface WiseDecoratorChain extends FieldDecorator {
	
	/**
	 * Sets the next decorator of the chain to be called.
	 * 
	 * @param decorator The decorator.
	 * @return This instance in order to allow chain method calls.
	 * @since 0.0.1
	 */
	WiseDecoratorChain setNext(FieldDecorator decorator);
	
}
