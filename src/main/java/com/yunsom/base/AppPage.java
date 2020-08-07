package com.yunsom.base;

import com.alibaba.fastjson.JSON;
import com.yunsom.util.HttpClientUtil;
import com.yunsom.util.PropertiesUtil;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AppPage extends Page {
    public AppPage(WebDriver driver) {
        super(driver);
        this.setLocalStorage("testToken", this.getLocalStorage());
    }

    /**
     * 给浏览器设置LocalStorage
     */

    public void setLocalStorage(String key, String value) {
        ChromeDriver c = (ChromeDriver) driver;
        LocalStorage ls = c.getLocalStorage();
        ls.setItem(key, value);
    }

    /**
     * 调接口获取LocalStorage
     */
    public String getLocalStorage() {
        String body = PropertiesUtil.GetValueByKey("localStorageBody");
        String response = null;

        HttpEntity entity = new HttpClientUtil().post(PropertiesUtil.GetValueByKey("localStoragePath"), body).getEntity();
        try {
            response = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(response).getJSONObject("data").getString("token");
    }

    /**
     * 底部确定按钮定位
     */
    public WebElement sure() {
        return driver.findElement(By.className("footerBtns-sure"));
    }

    /**
     * 选择圆框定位
     */
    public WebElement roundOption() {
        return driver.findElement(By.className("biz-square-check-btn-normal"));
    }

    /**
     * 用于点击多选后，选择已勾选项
     */
    public WebElement roundChecked() {
        return driver.findElement(By.className("biz-square-check-btn-selected tree-icon-checked"));
    }

    /**
     * 时间日期控件
     */
    public List<WebElement> date() {
        List<WebElement> lists = new LinkedList<>();
        //取消
        lists.add(
                driver.findElement(By.xpath("//*[@id=\"datePicker\"]/div/div/div[1]/div[1]")));
        //确定
        lists.add(
                driver.findElement(By.xpath("//*[@id=\"datePicker\"]/div/div/div[1]/div[3]")));
        return lists;
    }

    /**
     * 数字键盘
     */
    public List<WebElement> keypad() {
        List<WebElement> lists = new LinkedList<>();
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(4) > td:nth-child(2)")));//0
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(1) > td:nth-child(1)")));//1
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(1) > td:nth-child(2)")));//2
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(1) > td:nth-child(3)")));//3
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(2) > td:nth-child(1)")));//4
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")));//5
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(2) > td:nth-child(3)")));//6
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(3) > td:nth-child(1)")));//7
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(3) > td:nth-child(2)")));//8
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(3) > td:nth-child(3)")));//9
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(4) > td:nth-child(1)")));//.
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(4) > td:nth-child(3)")));//关闭
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(1) > td:nth-child(4)")));//删除
        lists.add(driver.findElement(By.cssSelector("#am-number-keyboard-container > div > table > tbody > tr:nth-child(3) > td:nth-child(4)")));//确定
        return lists;
    }

    public List<WebElement> selectOption() {
        return this.getElements("单选选项");
    }

    /**
     * 单选的打钩项
     */

    public WebElement checked() {
        return this.getElement("已勾选项");
    }

}
