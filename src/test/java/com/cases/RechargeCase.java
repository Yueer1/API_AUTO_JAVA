package com.cases;


import com.RestAssure.JsonPath;
import com.alibaba.fastjson.JSONPath;
import com.http.utils.*;
import com.pojo.CaseInfo;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-04 21:53
 * @Desc： 充值用例
 **/
public class RechargeCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
        //1.参数化替换
        paramsReplace(caseInfo);
        //2.数据库前置查询结果
        Object beforeSqlResult = SqlUtils.getSingResult(caseInfo.getSql());
        //3.调用接口
        //获取一个带有鉴权头的默认头
        Map<String, Object> headers = HttpUtils.getAuthorizationHeaders();
        String body = HttpUtils.call(caseInfo, headers);
        System.out.println(body);
        //4.断言响应结果
        boolean responseAssertResult = responseAssert(caseInfo, body);
        //5.添加接口回写内容
        addWriteBackData(caseInfo.getCaseId(), Constants.RESPONSE_CELLNUM, startSheetIndex, body);
        //6.数据库后置查询结果
        Object afterSqlResult = SqlUtils.getSingResult(caseInfo.getSql());
        //7.数据库断言
        boolean sqlAssertResult = sqlAssert(caseInfo, beforeSqlResult, afterSqlResult);
        //8.添加断言回写内容
        String AssertResult  = responseAssertResult && sqlAssertResult ?"success":"failed";
        addWriteBackData(caseInfo.getCaseId(), Constants.ASSERT_CELLNUM, startSheetIndex, AssertResult);
        //9.添加日志
    }

    private boolean sqlAssert(CaseInfo caseInfo, Object beforeSqlResult, Object afterSqlResult) {
        boolean flag = false;
        if (!StringUtils.isBlank(caseInfo.getSql())) {
            //200(充值之后的金额)-100（充值之前的金额）==  参数（jsonPath从Params中取出来）
            BigDecimal beforeResult = (BigDecimal) beforeSqlResult;
            BigDecimal afterResult = (BigDecimal) afterSqlResult;
            //（jsonPath从参数中取出来）
            String params = caseInfo.getParams();
            String amountStr = JSONPath.read(params, "$.amount").toString();
            BigDecimal amount = new BigDecimal(amountStr);
            //subtract：相减
            //compareTo   -1 0 -1
            //afterResult.subtract(beforeResult).compareTo(amount)==0表示两个数相等
            BigDecimal subtract = afterResult.subtract(beforeResult);
            //相等说明断言成功，否则说明断言失败
            System.out.println(beforeSqlResult);
            System.out.println(afterSqlResult);
            System.out.println(subtract);
            System.out.println(beforeResult);
            System.out.println(afterResult);
            if (subtract.compareTo(amount) == 0) {
                System.out.println("断言成功");
            } else {
                System.out.println("断言失败");
                flag = false;
            }
        }
            return flag;
        }


    @DataProvider
        public Object[] datas () throws Exception {
            return ExcelUtils.read(startSheetIndex, sheetNum);
        }

   /* @DataProvider
    public Object[][] datas(){
       Object[][] datas = {
                {"http://api.lemonban.com/futureloan/member/register",
                        "{\"mobile_phone\":\"13889995899\",\"pwd\":\"123456891\"}","post"},
                {"http://api.lemonban.com/futureloan/member/register",
                        "{\"mobile_phone\":\"13889995100\",\"pwd\":\"123456892\"}","post"},
                {"http://api.lemonban.com/futureloan/member/register",
                        "{\"mobile_phone\":\"13889995101\",\"pwd\":\"123456893\"}","post"},
        };
        return datas;
    }
*/

}
