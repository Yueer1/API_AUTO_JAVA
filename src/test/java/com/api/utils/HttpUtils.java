package com.api.utils;

import com.alibaba.fastjson.JSONObject;
import com.api.pojo.CaseInfo;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

/**
 * @Project: api_auto_recent
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-13 16:00
 * @Desc： 用来发送http请求
 **/
public class HttpUtils {

    private static Logger logger = Logger.getLogger(HttpUtils.class);

    /*返回默认请求头*/
    public static  Map<String,Object> getDefaultHeaders(){
        Map<String,Object> headers = new HashMap<>();
        headers.put("X-Lemonban-Media-Type", Constants.X_LEMONBAN_MEDIA_TYPE);
        headers.put("Content-Type", "application/json");
        return headers;
    }


    /**
     * 获取带有鉴权信息和默认信息的头
     * @return
     */
    public static  Map<String,Object> getAuthorizationHeaders(){
        Map<String, Object> headers = HttpUtils.getDefaultHeaders();
        //取出token,存储到headers中
        Object token = UserData.vars.get("${token}");
        headers.put("Authorization","Bearer "+token);
        return headers;
    }

/**http请求方法,并输出body
 * @param  caseInfo 请求参数
 * @param  headers  请求头
 * @return  响应体
 * **/
    public static String call(CaseInfo caseInfo, Map<String,Object> headers){
        //1、获取用例信息
        String url = caseInfo.getUrl();
        String params = caseInfo.getParams();
        String type = caseInfo.getType();
        String contentType = caseInfo.getContentType();
        String body = null;
        //2、判断请求类型
        if ("post".equalsIgnoreCase(type)) {
            //1、如果是form类型的参数
            if ("form".equalsIgnoreCase(contentType)) {
                params = json2KeyValue(params);
                //需要指定这个参数为form类型
                headers.put("Content-Type", "application/x-www-form-urlencoded");
            }
            body = HttpUtils.myPost(url, headers, params);
        }else if("get".equalsIgnoreCase(type)){
            body = HttpUtils.myGet(url, headers);
        }else if ("patch".equalsIgnoreCase(type)){
            body = HttpUtils.myPatch(url,headers,params);
        }
        logger.info(body);
        return body;
    }

    /**json字符串转成 key = value&key = value 格式
     * */
    private static String json2KeyValue(String params) {
           //2、json参数转成 key=value&key=value 参数
            //逻辑：jsonStr-->map-->keyValueStr
            //json转换成map
            Map<String,String> map = JSONObject.parseObject(params, Map.class);
            //取出map中所有的健
            String formParams = "";
            Set<String>  keys = map.keySet();
            for(String key : keys){
                //key=value&key=value
                formParams += key +"="+map.get(key)+"&";
            }
            //截取掉拼接的字符串中的最后一个&
            formParams.substring(0,formParams.length()-1);
            //System.out.println(formParams);
            params = formParams;
            return params;
        }

    /*get请求:接口地址+参数
    格式：url?paga=1      格式:member/11/info
    * */

    public static String  myGet(String url,Map<String, Object> headers){
        return given().headers(headers).get(url).asString();
    }

    public static String myPost(String url, Map<String, Object> headers, String params){
        return given().headers(headers).body(params).post(url).asString();
    }

    public static String myPatch(String url, Map<String, Object> headers, String params){
        return given().headers(headers).body(params).patch(url).asString();
    }
}
