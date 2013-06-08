package org.wiselenium.core.element.field.impl;

import org.wiselenium.core.element.field.Img;

/**
 * Implementation of a HTML Image.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public class ImgImpl extends BasicField<Img> implements Img {
	
	@Override
	public String getAlt() {
		return this.getAttribute("alt");
	}
	
	@Override
	public String getSrc() {
		return this.getAttribute("src");
	}
	
}
