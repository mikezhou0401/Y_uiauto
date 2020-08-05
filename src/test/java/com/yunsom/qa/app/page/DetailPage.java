package com.yunsom.qa.app.page;

import com.yunsom.base.AppPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailPage extends AppPage {
    public DetailPage(WebDriver driver) {
        super(driver);
    }


    public WebElement field1 = this.getElement("操作");
    public WebElement field2 = this.getElement("编辑");
    public WebElement field3 = this.getElement("删除");


}
