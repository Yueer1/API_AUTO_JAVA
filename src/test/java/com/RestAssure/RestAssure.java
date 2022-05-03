package com.RestAssure;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-04 21:53
 * @Desc： restAssure系列描述
 **/
public class RestAssure {
    public static void main(String[] args) {
        //1.最简单的get请求
        //链式编程：方法1（）.方法2（）.方法3（）.方法4（）
        //System.out.println(get("http://www.baidu.com").asString());
        //配置请求头或者请求体
        //get();

        /*注册
        System.out.println(given().header("X-Lemonban-Media-Type", "lemonban.v1")
                .header("Content-Type", "application/json")
                .body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}")
                .post("http://api.lemonban.com/futureloan/member/register").asString());
    }*/

        /*System.out.println(given().header("X-Lemonban-Media-Type", "lemonban.v1")
                .header("Content-Type", "application/json")
                .body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}")
                .post("http://api.lemonban.com/futureloan/member/login").asString());
    }*/

        //post();

        /*private static void get() {
        System.out.println(given()
                .header("X-Lemonban-Media-Type", "lemonban.v1")
                .get("http://api.lemonban.com/futureloan/loans?pageIndex=1&pageSize=2")
                .asString());*/


    /*private static void post() {
        //1.定义请求头
        Map<String,Object> headers = new HashMap<>();
        headers.put("X-Lemonban-Media-Type", "lemonban.v1");
        headers.put("Content-Type", "application/json");
        //2.发送post
        String body =
                given().headers(headers)
                .body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}")
                .post("http://api.lemonban.com/futureloan/member/login").asString();
        //3.打印响应
        System.out.println(body);
    }*/

        /*//patch();
        //1.发送patch请求，获取对应的response
         Response response =  given()
               .header("X-Lemonban-Media-Type", "lemonban.v1")
                .header("Content-Type", "application/json")
                .body("{\"member_id\":299,\"reg_name\":\"萌萌\"}")
                .patch("http://api.lemonban.com/futureloan/member/update");
       //2.获取对应的响应码、响应头、响应体
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Headers headers = response.getHeaders();
        System.out.println(headers);
        ResponseBody body = response.getBody();
        System.out.println(body);*/




    }

    /*private static void patch() {
        System.out.println(
                given().header("X-Lemonban-Media-Type", "lemonban.v1")
                        .header("Content-Type", "application/json")
                        .body("{\"member_id\":299,\"reg_name\":\"萌萌\"}")
                        .patch("http://api.lemonban.com/futureloan/member/update").asString());
    }*/
}