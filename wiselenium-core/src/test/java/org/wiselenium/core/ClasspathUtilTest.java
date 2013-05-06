package org.wiselenium.core;

import static org.wiselenium.core.ClasspathUtil.findImplementationClass;

import java.util.List;

import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class ClasspathUtilTest {
	
	@Test(expectedExceptions = NoElementImplementationFoundException.class)
	public void shouldThrowExceptionWhenNoImplementationIsFound() {
		findImplementationClass(List.class);
	}
	
}
