package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.pagefactory.ClasspathUtils.findImplementationClass;

import java.util.List;

import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class ClasspathUtilsTest {
	
	@Test(expectedExceptions = NoElementImplementationClassFoundException.class)
	public void shouldThrowExceptionWhenNoImplementationIsFound() {
		findImplementationClass(List.class);
	}
	
}
