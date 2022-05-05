package com.api.cases;


import com.api.pojo.CaseInfo;
import com.api.utils.Constants;
import com.api.utils.ExcelUtils;
import com.api.utils.HttpUtils;
import com.api.utils.SQLUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project: api_auto_recent
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-13 16:17
 * @Desc： 注册用例
 **/
public class RegisterCase extends BaseCase{

    private Logger logger = Logger.getLogger(RegisterCase.class);

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo){

        paramsReplace(caseInfo);
        //接口执行之前的sql值
        String sql = caseInfo.getSql();
        Object beforesingleResult = SQLUtils.getSingleResult(sql);


        Map<String, Object> headers = HttpUtils.getDefaultHeaders();
        String body = HttpUtils.call(caseInfo, headers);
        System.out.println(body);

        //响应断言
        boolean resultAssertFlag = ResultAssert(caseInfo, body);
        addWriteBackData(caseInfo.getCaseId(),Constants.RESPONSE_CELLNUM,StartSheetIndex,body);

        //接口执行之后的SQL值
        Object aftersingleResult = SQLUtils.getSingleResult(sql);
        boolean sqlAssertFlag = SQLAssert(caseInfo, beforesingleResult, aftersingleResult);

        //添加断言的结果：响应结果和数据库结果一起判断
        String finalAssert = resultAssertFlag && sqlAssertFlag?"passed":"failed";
        addWriteBackData(caseInfo.getCaseId(), Constants.FINALAssert_CELLNUM,StartSheetIndex,finalAssert);

    }

    /**
     * 根据接口执行前后sql语句的值，来判断sql断言
     * @param caseInfo
     * @param beforesingleResult   接口执行前的实际值
     * @param aftersingleResult    接口执行后的实际值
     */
    private boolean SQLAssert(CaseInfo caseInfo, Object beforesingleResult, Object aftersingleResult) {
        boolean sqlResult = false;
        Long beforesqlResult = (Long) beforesingleResult;
        Long aftersqlResult = (Long) aftersingleResult;
        if(!StringUtils.isBlank(caseInfo.getSql())) {
            if (beforesqlResult == 0 && aftersqlResult == 1) {
                System.out.println("数据库断言成功");
                sqlResult = true;
            } else {
                System.out.println(beforesqlResult);
                System.out.println(aftersqlResult);
                System.out.println("数据库断言失败");
                sqlResult=false;
            }
        }
        return sqlResult;
    }

    public void sqlAssert(){


    }

    @DataProvider
    public Object[] datas() throws Exception{
        //1、从Excel中读取数据
        return ExcelUtils.read(StartSheetIndex,SheetNum);
    }


/*    @DataProvider
    public Object[][] datas(){
        Object[][] datas = {
                {"http://api.lemonban.com/futureloan/member/register",
                        "{\"mobile_phone\":\"13889995899\",\"pwd\":\"123456891\"}","post"},
                {"http://api.lemonban.com/futureloan/member/register",
                        "{\"mobile_phone\":\"13889995100\",\"pwd\":\"123456892\"}","post"},
                {"http://api.lemonban.com/futureloan/member/register",
                        "{\"mobile_phone\":\"13889995101\",\"pwd\":\"123456893\"}","post"}
        };
       return datas;
    }*/

}
