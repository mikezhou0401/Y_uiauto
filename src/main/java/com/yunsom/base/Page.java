package com.yunsom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName: Page
 * @Description: 页面初始化
 */
public class Page extends Locator {
    public Page(WebDriver driver) {
        super(driver);
        this.setYamlFile(this.getClass().getSimpleName());
        this.getYamlFile();
    }

    protected Actions getAction() {
        return new Actions(driver);
    }

    /**
     * enter键
     */
    public void clickEnter() {
        this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).perform();
        this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.ENTER).perform();
    }

    /**
     * @param
     * @return void    返回类型
     * @throws
     * @Title: clickEND
     * @Description:点击END
     */
    public void clickEND() {
        this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.END).perform();
    }

    /**
     * @param @param count    设定文件
     * @return void    返回类型
     * @throws
     * @Title: clickPageUp
     * @Description:向上翻页
     */
    public void clickPageUp(int count) {
        for (int i = 0; i < count; i++) {
            this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
            this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
        }
    }

    /**
     * @param
     * @return void    返回类型
     * @throws
     * @Title: clickPageDown
     * @Description:向下翻页
     */
    public void clickPageDown(int count) {
        for (int i = 0; i < count; i++) {
            this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
            this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
        }
    }

    public void refresh() {
        driver.navigate().refresh();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
