package com.zhubajie.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author chenggang
 * @Date 17/4/18 上午10:26
 */

public class PropertiesUtil {
    static String filePath = "src/main/resources/config.properties";
    /**
     * 通过key获取对应的value
     * @param key
     * @return
     */
    public static String GetValueByKey(String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            String value = pps.getProperty(key);
            return value;
        }catch (IOException e) {
            System.out.println("请检查config.properties...");
            return null;
        }
    }
}