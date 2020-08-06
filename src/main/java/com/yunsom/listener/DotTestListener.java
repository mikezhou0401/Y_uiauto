package com.yunsom.listener;

import com.yunsom.base.BaseAction;
import com.yunsom.screenshot.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class DotTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            BaseAction tb = (BaseAction) tr.getInstance();
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
