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
 * @Create: 2020-09-05 00:49
 * @Desc： restassured的描述和练习
 **/
public class RestAssuredExce {
    public static void main(String[] args) {
        String body = given().header("X-Lemonban-Media-Type", "lemonban.v1")
                .header("Content-Type", "application/json")
                .body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}")
                .post("http://api.lemonban.com/futureloan/member/login").asString();
        //from(body).getInt("data.id");
        Object o = from(body).get("data.id");
        System.out.println(o);
        System.out.println("===================================");

        info();
        System.out.println("===================================");
        withdraw();
        System.out.println("===================================");
        patch();

    }

    //提现部分
    private static void withdraw() {
        System.out.println(
                given()
                        .header("X-Lemonban-Media-Type", "lemonban.v1")
                        .body("{\"member_id\":2955\",amount\":6200}")
                        .post("http://api.lemonban.com/futureloan/member/withdraw")
                        .asString());
    }

    //用户信息部分
    private static void info() {
        System.out.println(
                given().header("X-Lemonban-Media-Type", "lemonban.v1")
                        .get("http://api.lemonban.com/futureloan/member/12/info")
                        .asString());
    }
    //更改昵称
    private static void patch() {
        System.out.println(
                given().header("X-Lemonban-Media-Type", "lemonban.v1")
                        .header("Content-Type", "application/json")
                        .body("{\"member_id\":299,\"reg_name\":\"萌萌\"}")
                        .patch("http://api.lemonban.com/futureloan/member/update").asString());
    }
}
