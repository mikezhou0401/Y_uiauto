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

public class RecordProjectPage extends Page {
    private String PROJECT_GIT_GROUP_NAME = "git@git.zhubajie.la:qa-tester/java-zbj-qa1-dubbo.git";
    private String zhName = "devops自动化回归出版";
    private String projectName = "qatester";
    private String current = String.valueOf(RandomUtil.getRandom(100000000));
    /**
     * 构造方法
     * @param driver
     */
    public RecordProjectPage(WebDriver driver) {
        super(driver);
    }

    /**
     * 工程档案
     */
    public void projectDoc(){
        this.getElement("点击工程档案").click();
    }

    /**
     * 工程录入
     */
    public void recordProject(){
        this.getElement("点击工程录入").click();
        DateUtil.sleep(5);

    }

    /**
     * 系统名称
     */
    public void inputSystemName(){
        this.getElement("输入系统名称").sendKeys(projectName + current);
        System.out.println(projectName + current);
    }

    /**
     *中文简称
     */
    public void inputZhName(){
        this.getElement("输入中文简称").sendKeys(zhName);
    }

    /**
     * 语言
     */
    public void selectLanguage(){
        this.getElement("选择语言").click();
    }

    /**
     *项目类型
     */
    public void selectProjectType(){
        this.getElement("选择项目类型").click();
    }

    /**
     *工程类型
     */
    public void selectProjectTemplet(){
        this.getElement("选择工程类型").click();
    }

    /**
     *工程GIT地址
     */
    public void inputProjectGITGroupName(){
        this.getElement("输入工程GIT地址").sendKeys(PROJECT_GIT_GROUP_NAME);
        DateUtil.sleep(3);
    }

    /**
     *提交
     */
    public void submit(){
        this.getElement("点击提交").click();
        DateUtil.sleep(5);
    }

    /**
     * 输入系统名称
     */
    public void inputEngineName(){
        this.getElement("输入系统名称").sendKeys(projectName + current);
        System.out.println(projectName + current);
    }

    /**
     * 查询
     */
    public void query(){
        this.getElement("点击查询").click();
        DateUtil.sleep(5);
    }

    /**
     * 断言点－点击操作
     * @return
     */
    public boolean checkOpIsEnable(){
        return this.getElement("点击操作").isEnabled();
    }
}
