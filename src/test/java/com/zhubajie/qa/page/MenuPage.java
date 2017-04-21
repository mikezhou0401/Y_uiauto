package com.zhubajie.qa.page;

import com.zhubajie.base.Page;
import org.openqa.selenium.WebDriver;

/**
 * 菜单页面
 * @Author chenggang
 * @Date 17/4/20 下午1:49
 */

public class MenuPage extends Page {
    /**
     * 构造方法
     * @param driver
     */
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    /**
     * 工程管理
     */
    public void projectMgt(){
        this.getElement("点击工程管理").click();
    }

    /**
     * 构建管理
     */
    public void buildMgt(){
        this.getElement("点击构建管理").click();
    }
}
