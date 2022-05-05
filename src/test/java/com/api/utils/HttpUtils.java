package com.api.utils;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

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
