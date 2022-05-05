package com.api.cases;

import com.alibaba.fastjson.JSONPath;
import com.api.pojo.CaseInfo;
import com.api.utils.*;
import io.restassured.path.json.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Project: api_auto_recent_V2
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-22 16:25
 * @Desc： 充值部分用例
 **/
public class RechargeCase extends BaseCase {

    private Logger logger = Logger.getLogger(RechargeCase.class);

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo){
        paramsReplace(caseInfo);
        //请求之前的sql语句
        Object beforeSqlResult = SQLUtils.getSingleResult(caseInfo.getSql());
        //获取一个带有鉴权和默认的头
        Map<String, Object> headers = HttpUtils.getAuthorizationHeaders();
        String body = HttpUtils.call(caseInfo, headers);
        System.out.println(body);

        //响应断言
        boolean resultAssertFlag = ResultAssert(caseInfo, body);

        addWriteBackData(caseInfo.getCaseId(), Constants.RESPONSE_CELLNUM,StartSheetIndex,body);
        //接口请求之后的sql语句
        Object afterSqlResult = SQLUtils.getSingleResult(caseInfo.getSql());
        boolean sqlAssertFlag = sqlAssert(caseInfo, beforeSqlResult, afterSqlResult);
        //添加断言的结果：响应结果和数据库结果一起判断
        String finalAssert = resultAssertFlag && sqlAssertFlag?"passed":"failed";
        addWriteBackData(caseInfo.getCaseId(), Constants.FINALAssert_CELLNUM,StartSheetIndex,finalAssert);
    }

    public boolean sqlAssert(CaseInfo caseInfo,Object beforeSqlResult,Object afterSqlResult) {
        boolean flag = true;
        if(!StringUtils.isBlank(caseInfo.getSql())){
            BigDecimal beforeResult = (BigDecimal) beforeSqlResult;
            BigDecimal afterResult = (BigDecimal) afterSqlResult;
            //jsonpath 从params中取出来
            String params = caseInfo.getParams();
            String amountStr = JSONPath.read(params, "$.amount").toString();
            //Sting -》BigDecimal
            BigDecimal amount = new BigDecimal(amountStr);
            //afterResult.subtract(beforeResult).compareTo(amount)==0 表示两个数相等
            if(afterResult.subtract(beforeResult).compareTo(amount)==0){
                System.out.println("断言成功");
            }else {
                System.out.println("断言失败");
                flag =false;
            }
        }
        return flag;
    }

    @DataProvider
    public Object[] datas() throws Exception{
        //1、从Excel中读取数据
        return ExcelUtils.read(StartSheetIndex,SheetNum);

    }

}
