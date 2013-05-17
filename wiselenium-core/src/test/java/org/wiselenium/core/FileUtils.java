package org.wiselenium.core;

import java.io.File;

@SuppressWarnings("javadoc")
public final class FileUtils {
	
	public static String getAbsoluteFilePath(String relativePath) {
		return "file:///" + new File("").getAbsolutePath() + "/src/test/resources/" + relativePath;
	}
	
}
