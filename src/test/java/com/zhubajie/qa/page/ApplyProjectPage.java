package com.zhubajie.qa.page;

import com.zhubajie.base.Page;
import com.zhubajie.util.DateUtil;
import com.zhubajie.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 申请工程页面
 * @Author chenggang
 * @Date 17/4/20 下午1:49
 */

public class ApplyProjectPage extends Page {
    private String Maven_Group_Id = "com.zbj.qa.test";
    private String PROJECT_GIT_GROUP_NAME = "qa-tester";
    private String PHP_GIT_GROUP_NAME = "php-group";
    private String NODEJS_GIT_GROUP_NAME = "nodejs-group";
    private String zhName = "devops自动化回归出版";
    private String projectName = "qatester";
    private String department = "核心平台测试组";
    private String departmentId = "867";
    private String current = String.valueOf(RandomUtil.getRandom(100000000));
    /**
     * 构造方法
     * @param driver
     */
    public ApplyProjectPage(WebDriver driver) {
        super(driver);
    }

    /**
     * 申请工程
     */
    public void applyProject(){
        this.getElement("点击申请工程").click();
    }

    /**
     * 新增工程
     */
    public void addProject(){
        this.getElement("点击新增申请").click();
    }

    /**
     * 输入部门
     */
    public void inputDepartment(){
        DateUtil.sleep(3);
        this.updateAttribute("departmentId");
        this.getElement("输入部门ID").sendKeys(departmentId);
        DateUtil.sleep(3);
        this.removeAttribute("department");
        this.getElement("输入部门").sendKeys(department);
    }
    /**
     * 项目名称
     */
    public void inputProjectName(){
        this.getElement("选择语言").click();
        this.getElement("选择主业务").click();
        this.getElement("输入项目名称").sendKeys(projectName + current);
        System.out.println(projectName + current);
        this.getElement("选择类型").click();
    }

    /**
     *中文名称
     */
    public void inputZhName(){
        this.getElement("输入中文名称").sendKeys(zhName);
    }

    /**
     *MavenGroupId
     */
    public void inputMavenGroupId(){
        this.getElement("输入Maven_Group_Id").sendKeys(Maven_Group_Id);
    }

    /**
     *项目类型
     */
    public void selectProjectType(){
        this.getElement("选择项目类型").click();
    }

    /**
     *工程模版
     */
    public void selectProjectTemplet(){
        this.getElement("选择工程模板").click();
    }

    /**
     *工程git组
     */
    public void inputProjectGITGroupName(){
        this.getElement("输入PROJECT_GIT_GROUP_NAME").sendKeys(PROJECT_GIT_GROUP_NAME);
        DateUtil.sleep(1);
    }

    /**
     *php组
     */
    public void inputProjectPhpGroupName(){
        this.getElement("输入PHP_GIT_GROUP_NAME").sendKeys(PHP_GIT_GROUP_NAME);
        DateUtil.sleep(1);
    }

    /**
     *nodejs组
     */
    public void inputProjectNodeJsGroupName(){
        this.getElement("输入NODEJS_GIT_GROUP_NAME").sendKeys(NODEJS_GIT_GROUP_NAME);
        DateUtil.sleep(1);
        this.clickPageDown(1);
    }

    /**
     *提交
     */
    public void submit(){
        this.getElement("点击提交").click();
        DateUtil.sleep(10);
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
    /**
     * 操作
     */
    public void clickOp(){
        this.getElement("点击操作").click();
        DateUtil.sleep(3);
    }

    /**
     * 确认申请-再次确认申请-OK
     */
    public void confirm(){
        this.getElement("点击确认申请").click();
        DateUtil.sleep(2);
        this.getElement("点击再次确认申请").click();
        DateUtil.sleep(2);
        this.getElement("点击OK").click();
        DateUtil.sleep(5);
    }
}
