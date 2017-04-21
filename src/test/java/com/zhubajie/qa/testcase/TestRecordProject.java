package com.zhubajie.qa.testcase;

import com.zhubajie.base.TestBase;
import com.zhubajie.qa.check.CheckRecordProjectPage;
import com.zhubajie.qa.page.LoginPage;
import com.zhubajie.qa.page.MenuPage;
import com.zhubajie.qa.page.RecordProjectPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({com.zhubajie.report.ZBJReport.class,com.zhubajie.listener.TestngListener.class})
public class TestRecordProject extends TestBase {
	@Test(dataProvider="dataDriver",retryAnalyzer = com.zhubajie.listener.TestngRetry.class)
	public void testRecordProject(Map<String, String> caseParam){
		this.goTo(caseParam.get("url"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(caseParam.get("username"), caseParam.get("password"));
		MenuPage menuPage = new MenuPage(driver);
		menuPage.projectMgt();
		RecordProjectPage recordProjectPage = new RecordProjectPage(driver);
		recordProjectPage.projectDoc();
		recordProjectPage.recordProject();
		recordProjectPage.inputSystemName();
		recordProjectPage.inputZhName();
		recordProjectPage.selectLanguage();
		recordProjectPage.selectProjectType();
		recordProjectPage.selectProjectTemplet();
		recordProjectPage.inputProjectGITGroupName();
		recordProjectPage.submit();
		recordProjectPage.inputEngineName();
		recordProjectPage.query();
		CheckRecordProjectPage.checkRecordProjectOk(assertor,recordProjectPage.checkOpIsEnable());
	}
}
