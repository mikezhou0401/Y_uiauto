package com.yunsom.base;

import com.yunsom.util.Log;
import com.yunsom.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            driver = new InternetExplorerDriver(capabilities);
        } else if ("chrome".equals(PropertiesUtil.GetValueByKey("browserType"))) {
            if (PropertiesUtil.GetValueByKey("remoteOrNot").equals("no")) {
                ChromeOptions options = new ChromeOptions();
                if (System.getProperty("os.name").contains("Mac")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/files/chromedriver");
                    options.addArguments("--kiosk");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/files/chromedrivernew.exe");
                    options.addArguments("--test-type");
                    options.addArguments("--start-maximized");
                }
                driver = new ChromeDriver(options);
            } else {
                System.out.println("----------------------该模式为远程执行模式,分布式执行----------------------------------");
                DesiredCapabilities capability = null;
                capability = DesiredCapabilities.chrome();
                capability.setCapability("chrome.switches", Arrays.asList("--incognito"));
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("--test-type");
                capability.setCapability(ChromeOptions.CAPABILITY, options1);
                try {
                    driver = new RemoteWebDriver(new URL("http://172.26.8.168:4444/wd/hub"), capability);
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
