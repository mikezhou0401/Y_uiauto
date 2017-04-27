package com.zhubajie.report;

import com.zhubajie.util.DBBusiness;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author chenggang
 * @Date 17/4/19 上午8:43
 */

public class ZBJReport implements IReporter {
    /**
     * 生成报告
     * @param xmlSuites
     * @param suites
     * @param outputDirectory
     */
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        System.out.println("------->"+suites.toArray().toString());

        List<ITestResult> list = new ArrayList<ITestResult>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                list.addAll(this.listTestResult(failedConfig));
            }
        }
        this.sort(list);
        this.insertReport(list);
        //this.outputResult(list, outputDirectory + "/test.txt");
    }

    /**
     * 把结果写入到数据库，方便生成报告
     * @param list
     */
    private void insertReport(List<ITestResult> list){
        System.out.println("*********insertReport***********" + list.size());
        for (ITestResult result : list) {
            int costTime = Long.valueOf(result.getEndMillis()-result.getStartMillis()).intValue();
            String sql = "INSERT INTO zhubajie_qa.qa_ui (id,className,methodName,`status`,time,execTime) VALUES(0,'"+result.getTestClass().getRealClass().getName()+"','"+result.getMethod().getMethodName()+"','"+this.getStatus(result.getStatus())+"',NOW(),"+costTime+");";
            DBBusiness.update(sql);
        }
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap){
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<ITestResult>(results);
    }

    /**
     * 排序
     * @param list
     */
    private void sort(List<ITestResult> list){
        Collections.sort(list, new Comparator<ITestResult>() {
            public int compare(ITestResult r1, ITestResult r2) {
                if (r1.getStartMillis() > r2.getStartMillis()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    /**
     * 把日志记录到本地的文件
     * @param list
     * @param path
     */
    private void outputResult(List<ITestResult> list, String path){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(path)));
            StringBuffer sb = new StringBuffer();
            System.out.println("******outputResult**************"+list.size());
            for (ITestResult result : list) {
                if(sb.length()!=0){
                    sb.append("\r\n");
                }
                sb.append(result.getTestClass().getRealClass().getName())
                        .append(" ")
                        .append(result.getMethod().getMethodName())
                        .append(" ")
                        .append(this.formatDate(result.getStartMillis()))
                        .append(" ")
                        .append(result.getEndMillis()-result.getStartMillis())
                        .append("毫秒 ")
                        .append(this.getStatus(result.getStatus()));
            }
            output.write(sb.toString());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改状态
     * @param status
     * @return
     */
    private String getStatus(int status){
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "SUCCESS";
                break;
            case 2:
                statusString = "FAILURE";
                break;
            case 3:
                statusString = "SKIP";
                break;
            default:
                break;
        }
        return statusString;
    }

    /**
     * 日期格式
     * @param date
     * @return
     */
    private String formatDate(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

}
