package com.yunsom.qa.app.page;

import com.yunsom.base.AppPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InstanceListPage extends AppPage {
    public InstanceListPage(WebDriver driver) {
        super(driver);

    }
    public WebElement add(){
       return   this.getElement("新增");
    }

}
