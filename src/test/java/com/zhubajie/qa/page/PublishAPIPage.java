package com.zhubajie.qa.page;

import com.zhubajie.base.Page;
import com.zhubajie.util.DateUtil;
import com.zhubajie.util.RandomUtil;
import org.openqa.selenium.WebDriver;

/**
 * 工程档案页面
 * @Author chenggang
 * @Date 17/4/20 下午1:49
 */

public class PublishAPIPage extends Page {
    private String PROJECT_GIT_GROUP_NAME = "git@git.zhubajie.la:qa-tester/java-zbj-qa1-dubbo.git";
    private String zhName = "devops自动化回归出版";
    private String projectName = "java-zbj-love12-api";
    private String branch = "branch";
    /**
     * 构造方法
     * @param driver
     */
    public PublishAPIPage(WebDriver driver) {
        super(driver);
    }

    /**
     * 工程档案
     */
    public void publishApi(){
        this.getElement("点击发布API").click();
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
        DateUtil.sleep(3);
    }

    /**
     * 选择分支
     */
    public void selectBranch(){
        this.getElement("选择分支").click();
        this.getElement("输入分支").sendKeys(branch);
        this.clickEnter();
        DateUtil.sleep(10);
    }

    /**
     * 点击发布
     */
    public void publish(){
        this.getElement("点击发布").click();
        DateUtil.sleep(5);
    }
}
