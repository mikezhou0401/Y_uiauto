package com.zhubajie.qa.testcase;

import com.zhubajie.base.TestBase;
import com.zhubajie.qa.page.LoginPage;
import com.zhubajie.qa.page.MenuPage;
import com.zhubajie.qa.page.PublishAPIPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners({com.zhubajie.report.ZBJReport.class,com.zhubajie.listener.TestngListener.class})
public class TestPublishAPI extends TestBase {
	@Test(dataProvider="dataDriver",retryAnalyzer = com.zhubajie.listener.TestngRetry.class)
	public void testPublishAPI(Map<String, String> caseParam) throws Exception {
		try {
			this.goTo(caseParam.get("url"));
			LoginPage loginpage = new LoginPage(driver);
			loginpage.login(caseParam.get("username"), caseParam.get("password"));
			MenuPage menuPage = new MenuPage(driver);
			menuPage.buildMgt();
			PublishAPIPage publishAPIPage = new PublishAPIPage(driver);
			publishAPIPage.publishApi();
			publishAPIPage.inputEngineName();
			publishAPIPage.query();
			publishAPIPage.selectBranch();
			publishAPIPage.publish();
		} catch (Exception e) {
			throw new Exception("**********出错了，请检查代码！**********************");
		}
	}
}
