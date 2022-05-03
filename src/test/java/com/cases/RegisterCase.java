package com.cases;


import com.http.utils.Constants;
import com.http.utils.ExcelUtils;
import com.http.utils.HttpUtils;
import com.http.utils.SqlUtils;
import com.pojo.CaseInfo;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-04 21:53
 * @Desc： 注册用例
 **/
public class RegisterCase extends BaseCase {

    @Test(dataProvider = "datas")
    public void test(CaseInfo caseInfo) {
         //1.参数化替换
        paramsReplace(caseInfo);
        //2.数据库前置查询结果
        String sql = caseInfo.getSql();
        Object beforeSqlResult = SqlUtils.getSingResult(caseInfo.getSql());
        //3.调用接口
        Map<String, Object> headers = HttpUtils.getDefaultHeaders();
        String body = HttpUtils.call(caseInfo, headers);
        System.out.println(body);
        //4.断言响应结果
        boolean responseAssertResult = responseAssert(caseInfo, body);
        //5.添加接口回写内容
        addWriteBackData(caseInfo.getCaseId(),Constants.RESPONSE_CELLNUM,startSheetIndex,body);
        //6.数据库后置查询结果
        Object afterSqlResult = SqlUtils.getSingResult(caseInfo.getSql());
        //7.数据库断言
        //接口执行之前期望值是：0    接口执行之后期望值是：1
        boolean sqlAssertResult =  sqlAssert(caseInfo,beforeSqlResult,afterSqlResult);
        //8.添加断言回写内容
        String AssertResult  = responseAssertResult && sqlAssertResult ?   "passed":"failed";
        addWriteBackData(caseInfo.getCaseId(), Constants.ASSERT_CELLNUM, startSheetIndex, AssertResult);
        //9.添加日志

    }

    /**
     * 数据库断言
     * @param caseInfo      sql语句
     * @param beforeSqlResult   接口执行前的实际值
     * @param afterSqlResult    接口执行后的实际值
     */
    private boolean sqlAssert(CaseInfo caseInfo, Object beforeSqlResult, Object afterSqlResult) {
        boolean flag = true;
        //如果sql不为空，执行数据库断言
        if(!StringUtils.isBlank(caseInfo.getSql())){
            //转换数据类型
            Long beforeResult = (Long) beforeSqlResult;
            Long afterResult = (Long)afterSqlResult;
            boolean sqlAssertResult = false;
            //数据类型转换
            //接口执行之前期望值：0   接口执行之后:1
            if(beforeResult == 0 && afterResult == 1){
                System.out.println("数据库断言成功");
                sqlAssertResult = true;
            }else{
                System.out.println(beforeResult);
                System.out.println(afterResult);
                System.out.println("数据库断言失败");
                flag = false;
            }
        }
        return flag;
    }


    @DataProvider
    public Object[] datas() throws Exception {
        return ExcelUtils.read(startSheetIndex,sheetNum);
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
