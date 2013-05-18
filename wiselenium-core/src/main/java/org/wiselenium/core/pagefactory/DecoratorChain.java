package org.wiselenium.core.pagefactory;

/**
 * Allows the decorators to be used in a chain of responsibility.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.0.1
 */
interface DecoratorChain extends ExtendedSeleniumDecorator {
	
	/**
	 * Sets the next decorator of the chain to be called.
	 * 
	 * @param decorator The decorator.
	 * @return This instance in order to allow chain method calls.
	 * @since 0.0.1
	 */
	DecoratorChain setNext(ExtendedSeleniumDecorator decorator);
	
}
