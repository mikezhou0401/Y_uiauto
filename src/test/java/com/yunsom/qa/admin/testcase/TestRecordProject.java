package com.yunsom.qa.admin.testcase;


import com.yunsom.base.TestBase;
import com.yunsom.qa.admin.page.AdminLoginPage;
import com.yunsom.report.YunsomReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;

@Listeners({YunsomReport.class, com.yunsom.listener.TestngListener.class})
public class TestRecordProject extends TestBase {
    @Test(dataProvider = "dataDriver", retryAnalyzer = com.yunsom.listener.TestngRetry.class)
    public void testLogin(Map<String, String> caseParam) {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        this.goTo(caseParam.get("url"));
        adminLoginPage.adminLogin(caseParam.get("username"), caseParam.get("password"));
    }
}
