package com.zhubajie.base;

import com.zhubajie.util.Log;
import com.zhubajie.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver {
	
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}	
	
	public SeleniumDriver() {
		this.initialDriver();
	}

	private void initialDriver(){
		if("firefox".equals(PropertiesUtil.GetValueByKey("browserType"))){
			System.setProperty("webdriver.firefox.bin",
					"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();
		}else if("ie".equals(PropertiesUtil.GetValueByKey("browserType"))){
			System.setProperty("webdriver.ie.driver", "src/test/resources/files/iedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	        capabilities.setCapability("ignoreProtectedModeSettings", true);
			driver = new InternetExplorerDriver(capabilities);
		}else if("chrome".equals(PropertiesUtil.GetValueByKey("browserType"))){
			if(PropertiesUtil.GetValueByKey("remoteOrNot").equals("no")){
				ChromeOptions options = new ChromeOptions();
				if(System.getProperty("os.name").contains("Mac")){
					System.setProperty("webdriver.chrome.driver", "/Users/chenggang/Library/chromedriver/chromedriver");
					options.addArguments("--kiosk");
				}else{
					System.setProperty("webdriver.chrome.driver", "src/test/resources/files/chromedriver.exe");
					options.addArguments("--test-type");
				}
				driver = new ChromeDriver(options);
			}else{
				System.out.println("----------------------该模式为远程执行模式,分布式执行----------------------------------");
				DesiredCapabilities capability = null;
				capability = DesiredCapabilities.chrome();
				RemoteBrowserBean remoteBrowserBean = new RemoteBrowserBean("chrome");
				try {
					driver = new RemoteWebDriver(
							new URL(remoteBrowserBean.getHubURL()), capability);
					capability.setBrowserName(remoteBrowserBean.getBrowserName());
					capability.setVersion(remoteBrowserBean.getVersion());
					capability.setCapability(remoteBrowserBean.getPlatform()[0],
					remoteBrowserBean.getPlatform()[1]);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}else{
			Log.logInfo(PropertiesUtil.GetValueByKey("browserType") + " 的值不正确，请检查！");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(PropertiesUtil.GetValueByKey("waitTime")).intValue(), TimeUnit.SECONDS);
	}	
}
