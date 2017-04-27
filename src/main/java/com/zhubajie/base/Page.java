package com.zhubajie.base;

import org.openqa.selenium.Keys;
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

	/**
	 * enter键
	 */
	public void clickEnter(){
		this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).perform();
		this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.ENTER).perform();
	}

	/**
	 *
	 * @Title: clickEND
	 * @Description:点击END
	 * @param
	 * @return void    返回类型
	 * @throws
	 */
	public void clickEND(){
		this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.END).perform();
	}

	/**
	 *
	 * @Title: clickPageUp
	 * @Description:向上翻页
	 * @param @param count    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void clickPageUp(int count){
		for(int i=0;i<count;i++){
			this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
			this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();
		}
	}

	/**
	 *
	 * @Title: clickPageDown
	 * @Description:向下翻页
	 * @param
	 * @return void    返回类型
	 * @throws
	 */
	public void clickPageDown(int count){
		for(int i=0;i<count;i++){
			this.getAction().keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
			this.getAction().keyUp(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
		}
	}
}
