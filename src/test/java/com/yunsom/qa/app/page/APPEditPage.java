package com.yunsom.qa.app.page;

import com.yunsom.base.AppPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class APPEditPage extends AppPage {
    public APPEditPage(WebDriver driver) {
        super(driver);
    }

    public WebElement field1 = this.getElement("单行文本");
    public WebElement field2 = this.getElement("单行文本联动");
    public WebElement field3 = this.getElement("单行文本2");
    public WebElement submit = this.getElement("提 交");
    public WebElement reference = this.getElement("引用对象");
    public WebElement department1 = this.getElement("部门1");
    public WebElement department2 = this.getElement("部门2");
    public WebElement date1 = this.getElement("日期");
    public WebElement date2 = this.getElement("日期时间");
    public List<WebElement> switch_ = this.getElements("正负值按钮");
    public List<WebElement> multiline = this.getElements("多行文本");
    public List<WebElement> singleSelectLists = this.getElements("单选");
    // public List<WebElement> multipleSelect = this.getElements("多选");
    public List<WebElement> numbers = this.getElements("数值");
    public List<WebElement> selectOption = this.getElements("单选选项");
    public List<WebElement> checked = this.getElements("已勾选项");


}

