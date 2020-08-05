package com.yunsom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {
	
	public static Properties getProperties(String filePath) {
		InputStream inputStream = null;
		if ((filePath == null) || ("".equals(filePath))) {
			Log.logError("文件不存在...");
		} else {
			inputStream = FileUtil.class.getResourceAsStream(filePath);
		}
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			Log.logError(e.getMessage());
		}
		return properties;
	}
}
