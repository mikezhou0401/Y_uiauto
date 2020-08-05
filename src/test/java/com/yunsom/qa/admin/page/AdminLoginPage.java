package com.yunsom.qa.admin.page;

import com.yunsom.base.Page;
import com.yunsom.util.DateUtil;
import org.openqa.selenium.WebDriver;

/**
 * 登录页面
 */
public class AdminLoginPage extends Page {

	public AdminLoginPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * admin登录
	 * @param name
	 * @param pwd
	 */
	public void adminLogin(String name,String pwd){
		this.getElement("输入用户名").click();
		this.getElement("输入用户名").sendKeys(name);
		this.getElement("输入密码").sendKeys(pwd);
		this.getElement("输入验证码").sendKeys("0000");
		this.getElement("点击登陆").click();
		DateUtil.sleep(5);
	}
}
