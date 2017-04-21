package com.zhubajie.qa.testcase;

import com.zhubajie.base.TestBase;
import com.zhubajie.qa.check.CheckLoginPage;
import com.zhubajie.qa.page.ApplyProjectPage;
import com.zhubajie.qa.page.LoginPage;
import com.zhubajie.qa.page.MenuPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;
@Listeners({com.zhubajie.report.ZBJReport.class,com.zhubajie.listener.TestngListener.class})
public class TestApplyProject extends TestBase {
	@Test(dataProvider="dataDriver",retryAnalyzer = com.zhubajie.listener.TestngRetry.class)
	public void testApplyProject(Map<String, String> caseParam){
		this.goTo(caseParam.get("url"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(caseParam.get("username"), caseParam.get("password"));
		MenuPage menuPage = new MenuPage(driver);
		menuPage.projectMgt();
		ApplyProjectPage applyProjectPage = new ApplyProjectPage(driver);
		applyProjectPage.applyProject();
		applyProjectPage.addProject();
		applyProjectPage.inputProjectName();
		applyProjectPage.inputZhName();
		applyProjectPage.inputMavenGroupId();
		applyProjectPage.selectProjectType();
		applyProjectPage.selectProjectTemplet();
		applyProjectPage.inputProjectGITGroupName();
		applyProjectPage.inputProjectPhpGroupName();
		applyProjectPage.inputProjectNodeJsGroupName();
		applyProjectPage.submit();
		applyProjectPage.inputEngineName();
		applyProjectPage.query();
		applyProjectPage.clickOp();
		applyProjectPage.confirm();
	}
}
