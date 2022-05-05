package com.api.cases;


import com.api.utils.HttpUtils;
import org.testng.annotations.DataProvider;
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
public class RegisterCase {
    @Test(dataProvider = "datas")
    public void test(String url,String params,String requestType){
        //注册接口测试
        Map<String,Object> headers = new HashMap<>();
        headers.put("X-Lemonban-Media-Type","lemonban.v1");
        headers.put("Content-Type", "application/json");
        String body = null;
        if ("post".equalsIgnoreCase(requestType)) {
            body = HttpUtils.myPost(url, headers, params);
        }else if("get".equalsIgnoreCase(requestType)){
            body = HttpUtils.myGet(url, headers);
        }else if ("patch".equalsIgnoreCase(requestType)){
            body = HttpUtils.myPatch(url,headers,params);
        }


    }

    @DataProvider
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
    }

}
