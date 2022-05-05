package com.api.http;
/**
 * @Project: api_auto_recent
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-12 13:52
 * @Desc： restassured案例
 **/
//静态导入：不用类名，直接导入静态的方法
    import com.alibaba.fastjson.JSONPath;
    import io.restassured.http.Headers;
    import io.restassured.path.json.JsonPath;
    import io.restassured.response.*;
    import io.restassured.response.ResponseBody;

    import java.sql.SQLOutput;
    import java.util.HashMap;
    import java.util.Map;

    import static com.alibaba.fastjson.JSONObject.*;

    import static io.restassured.RestAssured.*;
    import static io.restassured.matcher.RestAssuredMatchers.*;
    import static io.restassured.path.json.JsonPath.*;
    import static org.hamcrest.Matcher.*;

public class RestassurcedDemo {
    public static void main(String[] args) {

        //Proxy();
       //Response();

    }

   /*设置代理
    private static void Proxy() {
        given().
                proxy("127.0.0.1",8888).
                header("X-Lemonban-Media-Type","lemonban.v1").
                get("http://api.lemonban.com/futureloan/loans").
                asString();
    }*/




    /*private static void JSONPath() {
        String body =  given().
                header("X-Lemonban-Media-Type","lemonban.v1").
                get("http://api.lemonban.com/futureloan/loans").
                asString();
        //from JsonPath方法  getInt 获取整数类 ---- 不推荐使用
        System.out.println(from(body).getInt("code"));
        System.out.println(from(body).getList("data"));
        //fastJson jsonPath
        System.out.println(JSONPath.read(body, "code"));
        System.out.println(JSONPath.read(body, "data"));
    }*/

    /*//抽取响应的一些内容
    private static void Response() {
        //1、发送post请求，获取对应的response响应
        Response response  =  given().
                //括号里面的headers是上面新建的headers，前面的是固定的方法
                        header("X-Lemonban-Media-Type","lemonban.v1").
                        header("Content-Type","application/json").
                        body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}").
                        patch("http://api.lemonban.com/futureloan/member/register");
        //2、获取响应头、体+状态码
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        System.out.println("##########");
        Headers headers = response.getHeaders();
        System.out.println(headers);
        System.out.println("##########");
        ResponseBody body = response.getBody();
        System.out.println(body);
    }
*/

    //1、最简单的get请求
       /* System.out.println(get("http://api.lemonban.com/futureloan/loans").asString());
        private static void get(){
        System.out.println(given().
               header("X-Lemonban-Media-Type","lemonban.v1").
               get("http://api.lemonban.com/futureloan/loans").
               asString());
        }*/

       //post请求
       //1、定义请求头
      /* private static void post(){
        Map<String,Object> headers = new HashMap<>();
        headers.put("X-Lemonban-Media-Type","lemonban.v1");
        headers.put("Content-Type","application/json");
        //2、发送post请求
        String body =  given().
                //括号里面的headers是上面新建的headers，前面的是固定的方法
                headers(headers).
                body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}").
                post("http://api.lemonban.com/futureloan/member/register").asString();
        //3、打印响应
        System.out.println(body);*/

  /*    *//* patch请求
       //1、定义请求头
       private static void post(){
        Map<String,Object> headers = new HashMap<>();
        headers.put("X-Lemonban-Media-Type","lemonban.v1");
        headers.put("Content-Type","application/json");
        //2、发送post请求
        String body =  given().
                *//**//*括号里面的headers是上面新建的headers，前面的是固定的方法*//*
                headers(headers).
                body("{\"mobile_phone\":\"13889995898\",\"pwd\":\"123456890\"}").
                patch("http://api.lemonban.com/futureloan/member/register").asString();
        //3、打印响应
        System.out.println(body);*/

   }
