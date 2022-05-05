package com.api.cases;

import com.api.pojo.CaseInfo;
import com.api.utils.Constants;
import com.api.utils.ExcelUtils;
import com.api.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @Project: api_auto_recent_V4
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-05-01 18:30
 * @Desc： 审核项目用例类
 **/
public class AuditCase extends BaseCase {
    private Logger logger = Logger.getLogger(LoginCase.class);

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo){
        //1、参数化替换
        paramsReplace(caseInfo);

        Map<String, Object> headers = HttpUtils.getAuthorizationHeaders();;
        String body = HttpUtils.call(caseInfo, headers);
//        System.out.println(body);

/*        response2UserData(body,"$.data.id","${loan_id}");*/

        //Fastjson JsonPath
        //read(字符串，Jsonpath表达式)
        //从响应体中获取token


        //响应断言
        boolean resultAssertFlag = ResultAssert(caseInfo, body);

        addWriteBackData(caseInfo.getCaseId(), Constants.RESPONSE_CELLNUM,StartSheetIndex,body);

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
