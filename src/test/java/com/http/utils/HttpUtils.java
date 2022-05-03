package com.http.utils;

import com.alibaba.fastjson.JSONObject;
import com.cases.BaseCase;
import com.pojo.CaseInfo;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import static io.restassured.RestAssured.*;

/**
 *
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-05 00:31
 * @Desc： JsonPath
 **/

public class HttpUtils {
    //打印日志初始化
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    /*获取摸默认头的方法
    * */
    public static Map<String,Object> getDefaultHeaders(){
        Map<String,Object>  headers = new HashMap<>();
        headers.put("X-Lemonban-Media-Type", Constants.X_LEMONBAN_MEDIA_Type);
        headers.put("Content-Type", "application/json");
        return headers;
    }

    //获取带有鉴权信息和默认信息的头
    public static Map<String,Object> getAuthorizationHeaders(){
        Map<String, Object> headers = getDefaultHeaders();
        //取出token，存到 headers 中
        Object token = UserData.vars.get("${token}");
        headers.put("Authorization","Bearer "+token);
        return headers;
    }

    /*http请求方法：并输出响应体
          caseInfo    请求参数
          headers    请求头
          return 响应体
        * */
    public static String call(CaseInfo caseInfo,Map<String,Object>  headers){
        //1.获取请求参数
        String params = caseInfo.getParams();
        String contentType = caseInfo.getContentType();
        String type = caseInfo.getType();
        String url = caseInfo.getUrl();
        String body=null;
        //2.判断请求类型
        if("post".equalsIgnoreCase(type)) {
            //1.如果是form类型的参数
            if ("from".equalsIgnoreCase((type))){
                params = Json2KeyValue(params);
                headers.put("Content-Type","application/x-www-form-urlencoded");
            }

            body = HttpUtils.myPost(url, headers, params);
        }else if("get".equalsIgnoreCase(type)){
            body = HttpUtils.myGet(url, headers);
        }else if("patch".equalsIgnoreCase(type)){
            body = HttpUtils.myPatch(url, headers, params);
        }
        logger.info(body);
        return body;
    }

/*json字符串转化为key=value的字符串
例子：{"mobile_phone":"13877788825","pwd":"12345678"}=》
            //    mobile_phone=13877788825&pwd=12345678
            //jsonStr => map => keyValueStr
* */
    private static String Json2KeyValue(String params) {
            //2.将json转化为form，json=>key=value&key=value
            //将json转化为map
            Map<String,String> map = JSONObject.parseObject(params, Map.class);
            //获取所有的键
            Set<String> keySet = map.keySet();
            String formparams = "";
            for(String key : keySet){
                //key=value
                formparams += key + "=" + map.get(key) + "&";
            }
            //删除最后一个字符串
            formparams = formparams.substring(0, formparams.length() - 1);
            System.out.println(formparams);
            //参数重新赋值params = formparams;
            return params = formparams;
    }


    /*
get请求
url:接口地址        接口地址+参数、member/11/info
headers        请求头
@return        返回:响应，如果需要响应头，不调用asString即可
* */
    //静态方法可以使用类名.调用，使用起来比较方便
    public static String myGet(String url, Map<String, Object> headers){
        //headers这里要特别的注意，不能写成header
        return given().headers(headers).get(url).asString();
    }

/*post请求
url:接口地址        接口地址
headers        请求头
body           参数：json格式、form参数
@return        返回:响应，如果需要响应头，不调用asString即可
* */

    public static String myPost(String url, Map<String,Object> headers, String params){
        return given().headers(headers).body(params).post(url).asString();
    }

/*post请求
url:接口地址        接口地址
headers        请求头
body           参数：json格式
@return        返回:响应，如果需要响应头，不调用asString即可

* */

    public static String myPatch(String url, Map<String,Object> headers, String params){
        return given().headers(headers).body(params).patch(url).asString();
    }


}


