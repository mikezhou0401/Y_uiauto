package com.zhubajie.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 
* @ClassName: RetryListener 
* @Description:监听重跑机制 
* @author 程钢  chenggang@zhubajie.com 
* @date 2017年04月19日 上午11:15:25
*
 */
public class RetryListener implements IAnnotationTransformer{
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(TestngRetry.class);
		}
	}
}
