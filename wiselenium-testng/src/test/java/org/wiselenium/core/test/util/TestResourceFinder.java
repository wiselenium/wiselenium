package org.wiselenium.core.test.util;

import java.io.File;

@SuppressWarnings("javadoc")
public final class TestResourceFinder {
	
	public static String getAbsolutePath(String fileName) {
		String absolutePath = "file:///" + new File("").getAbsolutePath() + "/src/test/resources/"
			+ fileName;
		return absolutePath.replaceAll("\\\\", "/");
	}
	
}
