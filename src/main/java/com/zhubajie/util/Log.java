package com.zhubajie.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

/**
 * 日志工具类
 * Author:chenggang
 * Date:2017-04-16
 */
public class Log {
	private static Logger logger;
    private static String filePath = "src/main/resources/log4j.properties";
    
    static{
    	 logger = Logger.getLogger("devops");
         PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
    }

    /**
     * info
     * @param message
     */
    public static void logInfo(Object message) {       
        logger.info(message);
        //Reporter.log(DateUtil.getSimpleDateFormat() + " : " + message);
    }

    /**
     * logError
     * @param message
     */
    public static void logError(Object message) {        
        logger.error(message);
        //Reporter.log(DateUtil.getSimpleDateFormat() + " : " + message);
    }

    /**
     * logWarn
     * @param message
     */
    public static void logWarn(Object message) {        
        logger.warn(message);
        //Reporter.log(DateUtil.getSimpleDateFormat()+" : "+message);
    }
}
