package com.api.cases;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.api.pojo.CaseInfo;
import com.api.pojo.WriteBackData;
import com.api.utils.ExcelUtils;
import com.api.utils.UserData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.Map;
import java.util.Set;

/**
 * @Project: api_auto_recent_V2
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-18 11:14
 * @Desc： 用例父类
 **/
public class BaseCase {

    private Logger logger = Logger.getLogger(BaseCase.class);

    //定义开始索引-成员变量
    public int StartSheetIndex;
    //定义索引个数-成员变量
    public int SheetNum;

    @BeforeSuite
    public void setup(){
        UserData.vars.put("${register_mb}", ChineseAddressGenerator.getInstance().generate());
        UserData.vars.put("${register_pwd}","123456");
        UserData.vars.put("${login_mb}","13218890999");
        UserData.vars.put("${login_pwd}","123456");
        UserData.vars.put("${amount}","400");
        //member_id和token是通过登录接口获取，不需要提前写死
    }

    /*该方法只是实现了将变量在方法执行之前执行
     * */
    @BeforeClass
    @Parameters({"StartSheetIndex", "SheetNum"})
    public void beforeClass(int StartSheetIndex, int SheetNum) {
        this.StartSheetIndex = StartSheetIndex;
        this.SheetNum = SheetNum;
    }

    @AfterSuite
    public void tearDown() throws Exception {
        //批量回写
        ExcelUtils.batchwrite();
    }

    /**
     * @param body 返回报文体
     * @param path 获取信息的json路径
     * @param key  存储到key
     */
    public void response2UserData(String body, String path, String key) {
        //从响应体中获取JSONPath对应的值
        Object value = JSONPath.read(body, path);
        if (value != null) {
            UserData.vars.put(key, value);
        }
    }

    /**
     * 接口响应断言，直接多字段
     * @param caseInfo   获取期望值的变量
     * @param body     响应体
     */
    public boolean ResultAssert(CaseInfo caseInfo, String body) {
        //定义一个断言结果
        boolean assertResult = true;
        //取出期望的结果
        //取出来的是一个String,需要转化为Map
        String exceptedResult = caseInfo.getExceptedResult();
        //转化为Map<String,Object>
        Map<String,Object> map = JSONObject.parseObject(exceptedResult, Map.class);
        //取出所有的key值（实际值表达式）
        Set<String> keySet = map.keySet();
        for (String actualJsonPath: keySet){
            //通过Key值取出期望值
            Object exceptedValue = map.get(actualJsonPath);
            //获取实际值：  实际值表达式+响应体-->获取实际值
            Object actualValue = JSONPath.read(body, actualJsonPath);
            if(!exceptedResult.equals(actualValue)){
                logger.error("断言失败，实际值是："+actualValue+",期望值是："+exceptedResult);
                assertResult=false;
            }else {
                System.out.println();
            }
        }
        if(assertResult){
            logger.info("响应断言成功");
        }
        return assertResult;
    }

    /**
     * 添加回写内容到wbdlist集合中
     *
     * @param rownum
     * @param cellnum
     * @param sheetIndex
     * @param context
     */
    public void addWriteBackData(int rownum, int cellnum, int sheetIndex, String context) {
        WriteBackData wbd = new WriteBackData(rownum, cellnum, sheetIndex, context);
        ExcelUtils.wbdlist.add(wbd);

    }

    /**
     * 参数化
     * @param caseInfo   需要替换的参数
     */
    public void paramsReplace(CaseInfo caseInfo) {
        String url = caseInfo.getUrl();
        String params = caseInfo.getParams();
        String exceptedResult = caseInfo.getExceptedResult();
        String sql = caseInfo.getSql();
        //获取vars中所有的占位符
        Set<String> keySet = UserData.vars.keySet();
        for (String placeholder:keySet){
            String value = UserData.vars.get(placeholder).toString();
            //如果url不为空，占位符和实际值进行替换
            if(!StringUtils.isBlank(url)) {
                url = url.replace(placeholder, value);
            }
            if(!StringUtils.isBlank(params)) {
                params = params.replace(placeholder, value);
            }
            if(!StringUtils.isBlank(exceptedResult)) {
                exceptedResult = exceptedResult.replace(placeholder, value);
            }
            if(!StringUtils.isBlank(sql)) {
                sql = sql.replace(placeholder, value);
            }
            //重新赋值参数化之后的值
            caseInfo.setUrl(url);
            caseInfo.setParams(params);
            caseInfo.setExceptedResult(exceptedResult);
            caseInfo.setSql(sql);


        }
    }

}
