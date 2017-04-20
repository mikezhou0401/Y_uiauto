package com.zhubajie.listener;

import com.zhubajie.base.TestBase;
import com.zhubajie.screenshot.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @Author chenggang
 * @Date 17/4/19 下午4:17
 */

public class DotTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            TestBase tb = (TestBase) tr.getInstance();
            WebDriver driver = tb.getDriver();
            System.out.println(driver.getTitle());
            //截图
            new ScreenShot(driver).takeScreenshot();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
