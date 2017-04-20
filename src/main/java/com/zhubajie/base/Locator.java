package com.zhubajie.base;
import com.zhubajie.util.Log;
import com.zhubajie.util.PropertiesUtil;
import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenggang on 17/4/17.
 */
public class Locator {
    private Map<String, Map<String, String>> ml;
    private String yamlFile;
    protected WebDriver driver;
    private int waitTime = 0;

    public Locator(WebDriver driver) {
        this.driver = driver;
        waitTime = Integer.valueOf(PropertiesUtil.GetValueByKey("waitTime")).intValue();
    }

    public void setYamlFile(String yamlFile) {
        this.yamlFile = yamlFile;
    }

    /**
     *
     * @Title: getYamlFile
     * @Description:获取元素yaml文件
     * @return void 返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    protected void getYamlFile() {
        File f = new File("src/test/resources/locator/" + yamlFile + ".yaml");
        try {
            ml = Yaml.loadType(new FileInputStream(f.getAbsolutePath()),
                    HashMap.class);
        } catch (FileNotFoundException e) {
            Log.logError("src/test/resources/locator/" + yamlFile + ".yaml文件不存在");
        }
    }

    /**
     *
     * @Title: getBy
     * @Description:封装BY操作
     * @param @param type
     * @param @param value
     * @param @return 设定文件
     * @return By 返回类型
     * @throws
     */
    private By getBy(String type, String value) {
        By by = null;
        if (type.equals("id")) {
            by = By.id(value);
        }
        if (type.equals("name")) {
            by = By.name(value);
        }
        if (type.equals("xpath")) {
            by = By.xpath(value);
        }
        if (type.equals("className")) {
            by = By.className(value);
        }
        if (type.equals("linkText")) {
            by = By.linkText(value);
        }
        if (type.equals("css")) {
            by = By.cssSelector(value);
        }
        return by;
    }

    /**
     *
     * @Title: watiForElement
     * @Description:在等待时间内,一直等待元素出现
     * @param @param by
     * @param @return 设定文件
     * @return WebElement 返回类型
     * @throws
     */
    private WebElement watiForElement(final By by, String key) {
        WebElement element = null;
        try {
            element = new WebDriverWait(driver, waitTime)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });
        } catch (Exception e) {
            Log.logError(waitTime+"s等待"+key+"元素超时...");
        }
        return element;
    }

    public WebElement getElement(String key) {
        Log.logInfo(key);
        return this.getLocator(key, true);
    }

    private WebElement getLocator(String key,boolean wait) {
        WebElement element = null;
        if (ml.containsKey(key)) {
            Map<String, String> m = ml.get(key);
            String type = m.get("type");
            String value = m.get("value");
            By by = this.getBy(type, value);
            element = this.watiForElement(by, key);
        }else{
            Log.logError("检查元素" + key + "是否存在,"+"Locator " + key + " is not exist in "
                    + yamlFile + ".yaml");
        }
        return element;
    }
}
