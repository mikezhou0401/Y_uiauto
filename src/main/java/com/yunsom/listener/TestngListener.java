package com.yunsom.listener;

import com.yunsom.base.TestBase;
import com.yunsom.screenshot.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.*;

/**
 * 
 * @ClassName: TestngListener
 * @Description:运行错误监听类,并且自动截图保存

 *
 */
public class TestngListener extends TestListenerAdapter {

	/*
	 * (非 Javadoc) <p>Title: onTestFailure</p> <p>Description:运行失败 </p>
	 * 
	 * @param tr
	 * 
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		TestBase tb = (TestBase) tr.getInstance();
		WebDriver driver = tb.getDriver();
		new ScreenShot(driver).takeScreenshot();
	}

	/*
	 * (非 Javadoc) <p>Title: onTestSkipped</p> <p>Description: 运行跳过</p>
	 * 
	 * @param tr
	 * 
	 * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		TestBase tb = (TestBase) tr.getInstance();
		WebDriver driver = tb.getDriver();
		new ScreenShot(driver).takeScreenshot();
	}

	/*
	 * (非 Javadoc) <p>Title: onTestSuccess</p> <p>Description: 运行成功</p>
	 * 
	 * @param tr
	 * 
	 * @see org.testng.TestListenerAdapter#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
	}

	/*
	 * (非 Javadoc) <p>Title: onTestStart</p> <p>Description:运行开始 </p>
	 * 
	 * @param tr
	 * 
	 * @see org.testng.TestListenerAdapter#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
	}

	/*
	 * (非 Javadoc) <p>Title: onFinish</p> <p>Description: 运行结束</p>
	 * 
	 * @param testContext
	 * 
	 * @see org.testng.TestListenerAdapter#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		// List of test results which we will delete later
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
			//Log.logInfo("PassedTests = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}
		
		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
			//Log.logInfo("failedTest = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);
			// if we saw this test as a failed test before we mark as to be deleted
			// or delete this failed test if there is at least one passed version
			if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}
		// finally delete all tests that are marked
				for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext();) {
					ITestResult testResult = iterator.next();
					if (testsToBeRemoved.contains(testResult)) {
						//Log.logInfo("Remove repeat Fail Test: " + testResult.getName());
						iterator.remove();
					}
				}
	}
	
	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
		return id;
	}
}