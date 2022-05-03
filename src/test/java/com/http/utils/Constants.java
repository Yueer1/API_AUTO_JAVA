package com.http.utils;

import org.apache.xmlbeans.SchemaTypeSystem;

/**
 *
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-05 00:31
 * @Desc： 常量类
 **/

public class Constants {
    //用例文件地址
    public static final String ExcelPath = "src/test/resources/cases_v3.xlsx";
    //柠檬班特殊头
    public static final String X_LEMONBAN_MEDIA_Type = "lemonban.v2";
    //响应体回写列号
    public static final int RESPONSE_CELLNUM=8;

    //断言结果回写列号
    public static final int ASSERT_CELLNUM=10;

    //jdbcUrl
    public static final String JDBC_URL= "jdbc:mysql://api.lemonban.com:3306/futureloan?useUnicode=true&characterEncoding=utf-8";

    //jdbcUser
    public static final String JDBC_USER = "future";

    //jdbcPassword
    public static final String JDBC_PWD="123456";

}
