package com.api.utils;

/**
 * @Project: api_auto_recent_V2
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-17 18:13
 * @Desc： 常量类
 **/
public class Constants {
    //用例文件地址
    // public static final 字段类型：显示常量字段的取值
   public static final String EXCEL_PATH = "src/test/resources/cases_v3.xlsx";
   public static final String X_LEMONBAN_MEDIA_TYPE = "lemonban.v2";

   //响应体回写列号
    public static final int RESPONSE_CELLNUM=8;

    //最终断言结果回写列号
    public static final int FINALAssert_CELLNUM=10;

    //jdbcUrl
    public static final String JDBC_URL= "jdbc:mysql://api.lemonban.com:3306/futureloan?useUnicode=true&characterEncoding=utf-8";

    //jdbcUser
    public static final String JDBC_USER = "future";

    //jdbcPassword
    public static final String JDBC_PWD="123456";
}
