package com.zhubajie.qa.page;

import com.zhubajie.base.Page;
import org.openqa.selenium.WebDriver;

/**
 * 登录页面
 */
public class LoginPage extends Page {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * 登录
	 * @param name
	 * @param pwd
	 */
	public void login(String name,String pwd){
		this.getElement("输入用户名").sendKeys(name);
		this.getElement("输入密码").sendKeys(pwd);
		this.getElement("点击登陆").click();
	}
}
