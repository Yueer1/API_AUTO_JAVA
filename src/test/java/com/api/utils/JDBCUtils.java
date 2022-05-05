package com.api.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Project: api_auto_recent_V3
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-04-30 15:31
 * @Desc： JDBC工具类
 **/
public class JDBCUtils {

    public static Connection getConnection() {
        //定义数据库连接
        String url = Constants.JDBC_URL;
        String user = Constants.JDBC_USER ;
        String password = Constants.JDBC_PWD;
        //定义数据库连接对象
        Connection conn = null;

        try {
            //导入的数据库的驱动包
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
