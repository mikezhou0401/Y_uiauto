package com.zhubajie.listener;

import com.zhubajie.util.Log;
import com.zhubajie.util.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * 
 * @ClassName: TestngRetry
 * @Description:失败测试用例重新运行
 * @author 程钢 chenggang@zhubajie.com
 * @date 2017年04月19日 上午11:05:00
 *
 */
public class TestngRetry implements IRetryAnalyzer {
	// 默认次数
	private int retryCount = 1;
	// 最大次数
	private static int maxRetryCount;
	static {
		//从config文件获取最大运行次数
		maxRetryCount = Integer.valueOf(PropertiesUtil.GetValueByKey("retryNum")).intValue();
	}
	
	/*
	 * (非 Javadoc) 
	* <p>Title: retry</p> 
	* <p>Description: 重新运行</p> 
	* @param result
	* @return 
	* @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 */
	public boolean retry(ITestResult result) {
		if (retryCount <= maxRetryCount) {
			String message = "running retry for '" + result.getName()
					+ "' on class " + this.getClass().getName() + "Retrying "
					+ retryCount + " times";
			Log.logInfo("*******************测试发生错误、自动进行第" + retryCount + "次重试*******************");
			Log.logInfo(message);
			Reporter.setCurrentTestResult(result);
			retryCount++;
			return true;
		}
		return false;
	}
}
