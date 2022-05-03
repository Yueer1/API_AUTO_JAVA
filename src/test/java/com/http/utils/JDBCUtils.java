package com.http.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    public static Connection getConnection() {
        //定义数据库连接
        String url = Constants.JDBC_URL;
        String user = Constants.JDBC_USER ;
        String password = Constants.JDBC_PWD;
        //定义数据库连接对象
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


