package com.cases;

import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.http.utils.ExcelUtils;
import com.http.utils.UserData;
import com.pojo.CaseInfo;
import com.pojo.WriteBackData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.Map;
import java.util.Set;

public class BaseCase {
    //创建日志对象
    private static Logger logger = Logger.getLogger(BaseCase.class);
    //sheet开始索引
    public int startSheetIndex;
    //sheet个数
    public int  sheetNum;

    @BeforeSuite
    public void setup(){
        logger.info("====================自动化开始=============");
        UserData.vars.put("${register_pwd}", "12345678");
        UserData.vars.put("${register_mb}",ChineseMobileNumberGenerator.getInstance());
        UserData.vars.put("${login_mb}","12312332999");
        UserData.vars.put("${login_pwd}","12345678");
        //member_id和token是通过登录接口获取，不需要提前写死
        UserData.vars.put("${amount}","4000");
    }

    @BeforeClass
    @Parameters({"startSheetIndex","sheetNum"})
    public void beforeClass(int startSheetIndex,int sheetNum){
        //接受testng.xml中parameters参数
        this.startSheetIndex = startSheetIndex;
        this.sheetNum = sheetNum;
    }


    @AfterSuite
    public void tearDown() throws Exception{
        //批量回写
        ExcelUtils.batchWrite();
        System.out.println("===============");
    }

    /*添加回写内容到wbdList中*/
    public void addWriteBackData(int rowNum, int cellNum, int sheetIndex, String content) {
        //回写excel操作
        WriteBackData wbd = new WriteBackData(rowNum,cellNum,sheetIndex, content);
        ExcelUtils.wbdList.add(wbd);
    }

    /**
     * 通过jsonpath从响应体中取出值存储到userData中
     * */
    public void response2UserData(String body,String path,String key){
        //从响应体中获取jsonpath对应的值
        Object value = JSONPath.read(body,path);
        if(value != null){
            //把value存储到vars中
            UserData.vars.put(key,value);
        }
    }

    /**
     * 响应断言：期望结果和实际结果进行比较-支持多字段
     * @param caseInfo  从中获取期望值
     * @param body
     */
    public boolean responseAssert(CaseInfo caseInfo, String body) {
        boolean assertResult = true;
        String exceptResult = caseInfo.getExceptResult() ;
        //转Map<String,Object>
        Map<String,Object> map = JSONObject.parseObject(exceptResult, Map.class);
        //取出所有的key(实际值表达式)
        Set<String> keySet = map.keySet();
        for (String actualResult : keySet) {
            //通过key取出期望值
            Object exceptValue = map.get(actualResult);
            //实际值表达式+响应体=》获取实际值
            Object  actualValue = JSONPath.read(body, actualResult);
            //断言:期望值和实际值进行比较
            if(!exceptValue.equals(actualValue)) {
                //System.out.println("断言失败：期望值是：" + exceptValue + ",实际值是：" + actualValue);
                logger.error("断言失败：期望值是：" + exceptValue + ",实际值是：" + actualValue);
                //break;
                assertResult = false;
            }
        }
        if(assertResult){
            //System.out.println("响应断言成功");
            logger.info("响应断言成功");
        }
        return assertResult;
    }

    /**
     * 参数化抽取方法
     * @param caseInfo  需要替换的参数
     */
    public void paramsReplace(CaseInfo caseInfo) {
        String url = caseInfo.getUrl();
        String params = caseInfo.getParams();
        String exceptResult = caseInfo.getExceptResult();
        String sql = caseInfo.getSql();
        //获取vars中所有占位符
        Set<String> keySet = UserData.vars.keySet();
        for(String placeholder : keySet) {
            //{"member_id":"${member_id}","amount":"${amount}"}
            String value = UserData.vars.get(placeholder).toString();
            //如果url不为空，占位符和实际值进行替换
            if(StringUtils.isNotBlank(url)){
                url = url.replace(placeholder,value);
            }
            if(StringUtils.isNotBlank(params)){
                params = params.replace(placeholder,value);
            }
            if(StringUtils.isNotBlank(exceptResult)){
                exceptResult = exceptResult.replace(placeholder,value);
            }
            if(StringUtils.isNotBlank(sql)) {
                sql = sql.replace(placeholder, value);
            }
        }

        //参数化循环之后在循环外对字段进行赋值操作
        caseInfo.setUrl(url);
        caseInfo.setParams(params);
        caseInfo.setExceptResult(exceptResult);
        caseInfo.setSql(sql);
    }

}
