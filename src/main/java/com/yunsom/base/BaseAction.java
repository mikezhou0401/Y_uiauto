package com.yunsom.base;

import com.yunsom.util.Assertion;
import com.yunsom.util.ExcelUtil;
import com.yunsom.util.Log;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.*;

/**
 * 测试用例初始化
 */
public class BaseAction {
    protected Assertion assertor;
    protected WebDriver driver;
    private String filepath = "src/test/resources/testdata/";

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * 初始化webdriver
     */
    @BeforeClass
    public void initialDriver() {
        SeleniumDriver selenium = new SeleniumDriver();
        driver = selenium.getDriver();
        assertor = new Assertion();
        System.out.println("--------------START-----------------");
    }


    /**
     * 数据驱动
     *
     * @param
     * @return
     */
    @DataProvider(name = "dataDriver")
    public Iterator<Object[]> providerMethod() {
        Set<Object[]> set = new HashSet<Object[]>();
        try {
            String className = this.getClass().getSimpleName();
            File file = new File(filepath);
            String xlsx = file.getAbsolutePath() + "/" + className + ".xlsx";
            ExcelUtil excelUtil = new ExcelUtil();
            List<Map<String, Object>> list = excelUtil.readDataByRow(xlsx);
            System.out.println("---------用例参数列表为:" + list.toString());
            for (int i = 0; i < list.size(); i++) {
                Object[] object = new Object[1];
                object[0] = list.get(i);
                set.add(object);
            }
            Iterator<Object[]> iterator = set.iterator();
            return iterator;
        } catch (Exception e) {
            e.printStackTrace();
            return set.iterator();
        }
    }

    /**
     * 关闭webdriver
     */
    @AfterClass
    public void closeDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            System.out.println("--------------END-------------------");
        }
    }
    /**
     * @param @param url    设定文件
     * @return void    返回类型
     * @throws
     * @Title: goTo
     * @Description:输入测试地址
     */
    public void open(String url) {
        driver.get(url);
        Log.logInfo("打开浏览器,输入地址:" + url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对浏览器window进行进行封装
     */
    void setSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public void setPosition(int x, int y) {
        driver.manage().window().setPosition(new Point(x, y));
    }

    public Dimension getSize() {
        return driver.manage().window().getSize();
    }

    public Point getPosition() {
        return driver.manage().window().getPosition();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }


    /**
     * cookie&session
     */
    public void setCookie(String key, String value) {
        Cookie c = new Cookie(key, value);
        driver.manage().addCookie(c);
        Log.logInfo("添加cookie:" + key + ":" + value);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
