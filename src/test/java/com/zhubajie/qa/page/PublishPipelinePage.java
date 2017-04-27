package com.zhubajie.qa.page;

import com.zhubajie.base.Page;
import com.zhubajie.util.DateUtil;
import com.zhubajie.util.RandomUtil;

import org.eclipse.jetty.util.ajax.JSON;
import org.openqa.selenium.WebDriver;

/**
 * 工程档案页面
 * @Author pibing
 * @Date 17/4/20 下午1:49
 */

public class PublishPipelinePage extends Page {
    private String projectName = "java-zbj-xxx-dubbo";
    /**
     * 构造方法
     * @param driver
     */
    public PublishPipelinePage(WebDriver driver) {
        super(driver);
    }

    /**
     * 工程档案
     */
    public void publishPipeline(){
        this.getElement("点击发布流水线").click();
        DateUtil.sleep(10);
    }

    /**
     * 输入系统名
     */
    public void inputEngineName(){
        this.getElement("输入系统名").sendKeys(projectName);
    }

    /**
     * 查询
     */
    public void query(){
        this.getElement("点击查询").click();
        DateUtil.sleep(5);
    }
    
    /**
     * 点击dev
     */
    public void devClick(){
        this.getElement("点击dev").click();
        DateUtil.sleep(10);
    }
    
    /**
     * 输入tag
     */
    public void inputTagName(){
        this.getElement("输入tag").sendKeys("master");
    }
    
    
    /**
     * 查询
     */
    public void queryStructure(){
        this.getElement("点击立即构建").click();
        DateUtil.sleep(10);
       
    }
}
