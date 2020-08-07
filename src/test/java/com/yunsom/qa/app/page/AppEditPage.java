package com.yunsom.qa.app.page;

import com.yunsom.base.AppPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AppEditPage extends AppPage {
    public AppEditPage(WebDriver driver) {
        super(driver);
    }

    public WebElement field1() {
        return this.getElement("单行文本");
    }

    public WebElement field2() {
        return this.getElement("单行文本联动");
    }

    public WebElement field3() {
        return this.getElement("单行文本2");
    }

    public WebElement submit() {
        return this.getElement("提 交");
    }

    public WebElement reference() {
        return this.getElement("引用对象");
    }

    public WebElement department1() {
        return this.getElement("部门1");
    }

    public WebElement department2() {
        return this.getElement("部门2");
    }

    public WebElement date1() {
        return this.getElement("日期");
    }

    public WebElement date2() {
        return this.getElement("日期时间");
    }

    public List<WebElement> switch_() {
        return this.getElements("正负值按钮");
    }

    public List<WebElement> multiline() {
        return this.getElements("多行文本");
    }

    public List<WebElement> singleSelectLists() {
        return this.getElements("单选");
    }

    // public List<WebElement> multipleSelect = this.getElements("多选");
    public List<WebElement> numbers() {
        return this.getElements("数值");
    }




}

