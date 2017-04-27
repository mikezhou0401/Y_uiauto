package com.zhubajie.qa.testcase;

import com.zhubajie.base.TestBase;
import com.zhubajie.qa.page.LoginPage;
import com.zhubajie.qa.page.MenuPage;
import com.zhubajie.qa.page.PublishPipelinePage;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({com.zhubajie.report.ZBJReport.class,com.zhubajie.listener.TestngListener.class})
public class TestPublishPipeline extends TestBase {
	@Test(dataProvider="dataDriver",retryAnalyzer = com.zhubajie.listener.TestngRetry.class)
	public void testPublishPipeline(Map<String, String> caseParam){
			this.goTo(caseParam.get("url"));
			LoginPage loginpage = new LoginPage(driver);
			loginpage.login(caseParam.get("username"), caseParam.get("password"));
			MenuPage menuPage = new MenuPage(driver);
			menuPage.buildMgt();
			PublishPipelinePage publishPipelinePage = new PublishPipelinePage(driver);
			publishPipelinePage.publishPipeline();
			publishPipelinePage.inputEngineName();
			publishPipelinePage.query();
			publishPipelinePage.devClick();
			publishPipelinePage.inputTagName();
			publishPipelinePage.queryStructure();
	}
}
