package com.yunsom.qa.app.testcase;

import com.yunsom.base.AppPage;
import com.yunsom.base.BaseAction;
import com.yunsom.qa.app.page.AppEditPage;
import com.yunsom.qa.app.page.InstanceListPage;
import com.yunsom.report.YunsomReport;
import com.yunsom.util.PropertiesUtil;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;


@Listeners({YunsomReport.class, com.yunsom.listener.TestngListener.class})
public class DemoTest extends BaseAction {

    @Test(dataProvider = "dataDriver", retryAnalyzer = com.yunsom.listener.TestngRetry.class)
    public void test(Map<String, String> caseParam) {

        //进入实例列表
        this.open(PropertiesUtil.GetValueByKey("InstanceListPageURL"));
        InstanceListPage instanceListPage = new InstanceListPage(driver);
        instanceListPage.refresh();
        instanceListPage.add().click();


        AppEditPage appEditPage = new AppEditPage(driver);

        //单行
        appEditPage.field1().sendKeys("文本1");
        appEditPage.field2().sendKeys("文本2");
        appEditPage.field3().sendKeys("文本3");

        //多行
        for (WebElement element : appEditPage.multiline()) {
            element.sendKeys("是非得失辅导辅导费");
        }

        //单选(下拉单选自定义、下拉单选关联、人员单选)
        for (WebElement element : appEditPage.singleSelectLists()) {
            element.click();
            new AppPage(driver).selectOption().get(0).click();
        }

        appEditPage.date1().click();
        appEditPage.date().get(1).click();

        appEditPage.date2().click();
        appEditPage.date().get(1).click();


        /**
         * 正负数
         **/
        for (WebElement webElement : appEditPage.switch_()) {
            webElement.click();
        }

        /**
         * 输入数值
         **/
        for (WebElement webElement : appEditPage.numbers()) {
            webElement.click();
            sleep(200);
            appEditPage.keypad().get(1).click();
            appEditPage.keypad().get(13).click();
        }

        /**
         * 引用对象
         **/

        appEditPage.reference().click();
        sleep(1000);
        appEditPage.roundOption().click();
        appEditPage.sure().click();

        /**
         * 部门单
         **/
        appEditPage.department1().click();
        appEditPage.roundOption().click();
        appEditPage.sure().click();

        /**
         * 部门多
         **/
        appEditPage.department2().click();
        appEditPage.roundOption().click();
        appEditPage.sure().click();

        /**
         * 提交
         **/
        appEditPage.submit().click();
    }
}