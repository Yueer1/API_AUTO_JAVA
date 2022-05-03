package com.RestAssure;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static com.alibaba.fastjson.JSONObject.*;
import static io.restassured.path.json.JsonPath.*;

/**
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-05 00:31
 * @Desc： JsonPath
 **/


public class JsonPath {
    public static void  main(String[] args){
        /*String body = given().header("X-Lemonban-Media-Type", "lemonban.v1")
                .header("Content-Type", "application/json")
                .body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}")
                .post("http://api.lemonban.com/futureloan/member/login").asString();
        int anInt = from(body).getInt("data.id");
        System.out.println(anInt);*/
        System.out.println("你好啊");
        System.out.println("你好啊123");
}

}
