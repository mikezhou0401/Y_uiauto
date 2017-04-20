package com.zhubajie.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


/**
 * 
* @ClassName: Page 
* @Description: 页面初始化
* @author 程钢  chenggang@zhubajie.com 
* @date 2015年10月22日 下午3:54:06 
*
 */
public class Page extends Locator {
	public Page(WebDriver driver) {	
		super(driver);
		this.setYamlFile(this.getClass().getSimpleName());
		this.getYamlFile();
	}

	protected Actions getAction(){
		return new Actions(driver);
	}
}
