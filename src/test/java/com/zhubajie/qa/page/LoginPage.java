package com.zhubajie.qa.page;

import com.zhubajie.base.Page;
import com.zhubajie.util.DateUtil;
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
		DateUtil.sleep(5);
	}

	/**
	 * boss登录
	 */
	public void loginBoss(){
		this.getElement("输入boss用户名").sendKeys("zhiguan");
		this.getElement("输入boss密码").sendKeys("Zhiguan119");
		this.getElement("点击boss登陆").click();
		DateUtil.sleep(5);
	}
}
