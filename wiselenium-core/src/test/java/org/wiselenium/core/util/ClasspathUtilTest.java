package org.wiselenium.core.util;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.util.ClasspathUtil.findImplementationClass;

import java.util.List;

import org.testng.annotations.Test;
import org.wiselenium.core.element.field.Button;
import org.wiselenium.core.element.field.impl.ButtonImpl;

@SuppressWarnings("javadoc")
public class ClasspathUtilTest {
	
	@Test
	public void shouldFindImplementation() {
		Class<? extends Button> clazz = findImplementationClass(Button.class);
		assertEquals(clazz, ButtonImpl.class);
	}
	
	@Test(expectedExceptions = NoElementImplementationClassFoundException.class)
	public void shouldThrowExceptionWhenNoImplementationIsFound() {
		findImplementationClass(List.class);
	}
	
}
