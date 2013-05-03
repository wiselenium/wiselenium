package org.wiselenium.core.element.field;

/**
 * Represents a HTML Button.
 * 
 * @author Andre Ricardo Schaffer
 * @since
 */
public interface Button extends Field {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	Button and();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	Button click();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	Button doubleClick();
	
}
