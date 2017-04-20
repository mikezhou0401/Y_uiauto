package com.zhubajie.util;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Assertion {
	public static boolean flag = true;
	public static List<Error> errors = new ArrayList<Error>();
	
	public void verifyEquals(Object actual, Object expected){
		String methodInfo = "verifyEquals(" + String.valueOf(actual) + "," + String.valueOf(expected)+ ")";
		Assert.assertEquals(actual, expected);
		Log.logInfo(methodInfo);
	}
	
	public void verifyEquals(Object actual, Object expected, String message){
		String methodInfo = "verifyEquals(" + String.valueOf(actual) + "," + String.valueOf(expected) + "," + message + ")";
		Assert.assertEquals(actual, expected, message);
		Log.logInfo(methodInfo);
	}
	
	public void assertTrue(Boolean actual,String message){
		String methodInfo = "assertTrue(" + String.valueOf(actual) + "," + message + ")";
		Log.logInfo(methodInfo);
		Assert.assertTrue(actual,message);
	}

	public void assertTrue(Boolean actual){
		String methodInfo = "assertTrue(" + String.valueOf(actual)+")";
		Assert.assertTrue(actual);
		Log.logInfo(methodInfo);
	}
	
	public void assertFalse(Boolean actual,String message){
		String methodInfo = "assertFalse(" + String.valueOf(actual) + "," + message + ")";
		Assert.assertFalse(actual,message);
		Log.logInfo(methodInfo);
	}
}
