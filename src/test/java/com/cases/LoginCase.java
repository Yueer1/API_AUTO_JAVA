package com.cases;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.http.utils.Constants;
import com.http.utils.ExcelUtils;
import com.http.utils.HttpUtils;
import com.http.utils.UserData;
import com.pojo.CaseInfo;
import com.pojo.WriteBackData;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import javafx.scene.effect.SepiaTone;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-04 21:53
 * @Desc： 登录用例
 **/
public class LoginCase extends BaseCase{
    private Logger logger = Logger.getLogger(LoginCase.class);
    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo){
        //1.参数化替换
        paramsReplace(caseInfo);
        //2.数据库前置查询结果
        //3.调用接口
        Map<String, Object> headers = HttpUtils.getDefaultHeaders();
        String body = HttpUtils.call(caseInfo, headers);
        System.out.println(body);

        response2UserData(body,"$.data.token_info.token","${token}");
        //把member_id存到UserData中
        response2UserData(body,"$.data.id","${member_id}");
         //4.断言响应结果
        /*断言响应结果*/
        //{"$.code":0,"$.msg":"OK"}
        boolean responseAssertResult = responseAssert(caseInfo, body);
        //5.添加接口回写内容
        /*回写excel修改操作*/
        addWriteBackData(caseInfo.getCaseId(),Constants.RESPONSE_CELLNUM,startSheetIndex,body);
        //6.数据库后置查询结果
        //7.数据库断言
        String AssertResult  = responseAssertResult ?  "passed":"failed";
        addWriteBackData(caseInfo.getCaseId(), Constants.ASSERT_CELLNUM, startSheetIndex, AssertResult);
        //8.添加断言回写内容
        //9.添加日志
        Assert.assertEquals(AssertResult,"passed");
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

    @DataProvider
    public Object[] datas() throws Exception {
        return ExcelUtils.read(startSheetIndex,sheetNum);
    }


}
