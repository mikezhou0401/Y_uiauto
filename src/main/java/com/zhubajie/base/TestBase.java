package com.zhubajie.base;

import java.io.File;
import java.util.*;

import com.zhubajie.util.Assertion;
import com.zhubajie.util.DBBusiness;
import com.zhubajie.util.ExcelUtil;
import com.zhubajie.util.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
/**
 * 测试用例初始化
 */
public class TestBase{
	protected Assertion assertor;
	protected WebDriver driver;
	private String filepath = "src/test/resources/testdata/";
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * 初始化webdriver
	 */
	@BeforeClass
	public void initialDriver(){
		SeleniumDriver selenium = new SeleniumDriver();
		driver = selenium.getDriver();
		assertor = new Assertion();
		System.out.println("--------------START-----------------");
	}

	/**
	 * 数据驱动
	 * @param
	 * @return
	 */
	@DataProvider(name="dataDriver")
	public Iterator<Object[]> providerMethod(){
		Set<Object[]> set = new HashSet<Object[]>();
		try {
			String className = this.getClass().getSimpleName();
			File file = new File(filepath);
			String xlsx = file.getAbsolutePath()+"/" + className + ".xlsx";
			ExcelUtil excelUtil = new ExcelUtil();
			List<Map<String, Object>> list = excelUtil.readDataByRow(xlsx);
			System.out.println("---------用例参数列表为:"+list.toString());
			for (int i = 0; i <list.size() ; i++) {
				Object[] object = new Object[1];
				object[0] = list.get(i);
				set.add(object);
			}
			Iterator<Object[]> iterator = set.iterator();
			return iterator;
		} catch (Exception e) {
			e.printStackTrace();
			return set.iterator();
		}
	}

	/**
	 * 关闭webdriver
	 */
	@AfterClass
	public void closeDriver(){
		if(driver!=null){
			driver.close();
			driver.quit();
			System.out.println("--------------END-------------------");
		}
	}
	
	/**
	 * 
	* @Title: goTo 
	* @Description:输入测试地址
	* @param @param url    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void goTo(String url){
		driver.get(url);
		Log.logInfo("打开浏览器,输入地址:" + url);
	}
}
