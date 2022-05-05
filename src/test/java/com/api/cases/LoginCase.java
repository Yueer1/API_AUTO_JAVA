package com.api.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.api.pojo.CaseInfo;
import com.api.pojo.WriteBackData;
import com.api.utils.Constants;
import com.api.utils.ExcelUtils;
import com.api.utils.HttpUtils;
import com.api.utils.UserData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

/**
 * @Project: api_auto_recent_V2
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-17 18:05
 * @Desc： 登录接口
 **/
public class LoginCase extends BaseCase {

    private Logger logger = Logger.getLogger(LoginCase.class);

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo){
        //1、参数化替换
        paramsReplace(caseInfo);

        //定义请求头
        Map<String, Object> headers = HttpUtils.getDefaultHeaders();
        String body = HttpUtils.call(caseInfo, headers);
//        System.out.println(body);

        //Fastjson JsonPath
        //read(字符串，Jsonpath表达式)
        //从响应体中获取token

        //把token存到UserData中
        response2UserData(body,"$.data.token_info.token","${token}");
        //把memberID存到UserData中
        response2UserData(body,"$.data.id","${member_id}");

        //响应断言
        boolean resultAssertFlag = ResultAssert(caseInfo, body);

        addWriteBackData(caseInfo.getCaseId(),Constants.RESPONSE_CELLNUM,StartSheetIndex,body);

        //添加断言的结果：响应结果和数据库结果一起判断
        String finalAssert = resultAssertFlag ?"passed":"failed";
        addWriteBackData(caseInfo.getCaseId(), Constants.FINALAssert_CELLNUM,StartSheetIndex,finalAssert);

    }




    @DataProvider
    public Object[] datas() throws Exception{
        //1、从Excel中读取数据
        return ExcelUtils.read(StartSheetIndex,SheetNum);
    }



}
