package com.zhubajie.qa.testcase;

import com.zhubajie.base.TestBase;
import com.zhubajie.qa.check.CheckLoginPage;
import com.zhubajie.qa.page.LoginPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.Map;
@Listeners({com.zhubajie.report.ZBJReport.class,com.zhubajie.listener.TestngListener.class})
public class TestLogin extends TestBase {
	@Test(dataProvider="dataDriver",retryAnalyzer = com.zhubajie.listener.TestngRetry.class)
	public void testLogin(Map<String, String> caseParam) throws InterruptedException {
		this.goTo("https://bosslogin.t11.zbjdev.com/cp-dirlogin?back_url=http://devops.test.zbjdev.com/calllogin&appid=13353");
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(caseParam.get("username"),caseParam.get("password"));
		CheckLoginPage.checkLoginOk(assertor, true);
		Thread.sleep(5000);
	}
}
