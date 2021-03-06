package com.yunsom.base;

import com.yunsom.util.Log;
import com.yunsom.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public SeleniumDriver() {
        this.initialDriver();
    }

    private void initialDriver() {
        if ("firefox".equals(PropertiesUtil.GetValueByKey("browserType"))) {
            System.setProperty("webdriver.firefox.bin",
                    "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            driver = new FirefoxDriver();
        } else if ("ie".equals(PropertiesUtil.GetValueByKey("browserType"))) {
            System.setProperty("webdriver.ie.driver", "src/test/resources/files/iedriver.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            driver = new InternetExplorerDriver(options);
        } else if ("chrome".equals(PropertiesUtil.GetValueByKey("browserType"))) {
            ChromeOptions options = new ChromeOptions();
            if (PropertiesUtil.GetValueByKey("remoteOrNot").equals("no")) {
                if (System.getProperty("os.name").contains("Mac")) {
                    System.setProperty("webdriver.chrome.driver", PropertiesUtil.GetValueByKey("macChromePath"));
                    options.addArguments("--kiosk");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/files/chromedrivernew.exe");
                    options.addArguments("--test-type");
                    options.addArguments("--start-maximized");
                }
                driver = new ChromeDriver(options);
            } else {
                System.out.println("----------------------该模式为远程执行模式,分布式执行----------------------------------");
                options.setCapability("chrome.switches", Arrays.asList("--incognito"));
                options.addArguments("--test-type");
                options.setCapability(ChromeOptions.CAPABILITY, options);
                try {
                    driver = new RemoteStorageWebDriver(new URL("http://172.16.18.100:4446/wd/hub"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.logInfo(PropertiesUtil.GetValueByKey("browserType") + " 的值不正确，请检查！");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(PropertiesUtil.GetValueByKey("waitTime")).intValue(), TimeUnit.SECONDS);
    }
}
