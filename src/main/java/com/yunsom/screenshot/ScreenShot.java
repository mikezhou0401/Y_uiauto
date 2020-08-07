package com.yunsom.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenShot {
    public WebDriver driver;

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    private void takeScreenshot(String screenPath) {
        try {
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenPath));
        } catch (IOException e) {
            System.out.println("Screen shot error: " + screenPath);
        }
    }

    public void takeScreenshot() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String screenName = df.format(new Date()) + ".jpg";
        File dir = new File("test-output/snapshot");
        if (!dir.exists())
            dir.mkdirs();
        String screenPath = dir.getAbsolutePath() + "/" + screenName;
        this.takeScreenshot(screenPath);
    }

}
