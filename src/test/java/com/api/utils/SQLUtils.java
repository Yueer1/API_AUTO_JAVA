
package com.api.utils;

import com.api.pojo.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @Project: api_auto_recent_V3
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-04-30 15:50
 * @Desc： 数据库操作
 **/
public class SQLUtils {
    public static void main(String[] args) throws Exception {
        //DBUtils
//        String sql ="insert into member(reg_name,pwd,mobile_phone,type,leave_amount,reg_time) " +
//             "VALUES ('海贼王','25DJSGDJSJSJA400HSHSGAD','18888888888',1,10,NOW());";
//        updata(sql);

        MapHandler();

        BeanHandler();

        BeanListHandler();

        ScalarHandler();


    }

    public static Object getSingleResult(String sql){
        //如果sql为空，返回为null
        if (StringUtils.isBlank(sql)){
            return null ;
        }
        Object result = null;
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
//            Connection conn = JDBCUtils.getConnection();
            ScalarHandler handler = new ScalarHandler();
//        String sql = "select * from member where mobile_phone='13888888888'";
            result = runner.query(conn, sql, handler);
//        System.out.println(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return result;
    }

    private static void ScalarHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        ScalarHandler handler = new ScalarHandler();
        String sql = "select * from member where mobile_phone='13888888888'";
        Object result = runner.query(conn, sql, handler);
        System.out.println(result);
    }

    private static void BeanListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        BeanListHandler<Member> handler = new BeanListHandler<>(Member.class);
        String sql = "select * from member where mobile_phone='13888888888'";
        List<Member> result = runner.query(conn, sql, handler);
        for(Member member:result){
            System.out.println(result);
        }
    }

    private static void BeanHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        BeanHandler<Member> handler = new BeanHandler(Member.class);
        String sql = "select * from member where mobile_phone='13888888888'";
        Member member = runner.query(conn, sql, handler);
        System.out.println(member);
    }

    /**
     * 查询的代码
     * @throws SQLException
     */
    private static void MapHandler() throws SQLException {
        //查询
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        MapHandler handler = new MapHandler();
        String sql = "select * from member where mobile_phone='13888888888'";
        Map<String,Object> result = runner.query(conn,sql,handler);
        System.out.println(result);
    }


    private static void updata(String sql) throws SQLException {
        QueryRunner runner = new QueryRunner();
        //这个方法可以使用用来新增、修改、删除等操作
        Connection connection = JDBCUtils.getConnection();
//        String sql ="insert into member(reg_name,pwd,mobile_phone,type,leave_amount,reg_time) " +
//                "VALUES ('海贼王','25DJSGDJSJSJA400HSHSGAD','18888888888',1,10,NOW());";
        runner.update(connection,sql);
        JDBCUtils.close(connection);
    }
}
