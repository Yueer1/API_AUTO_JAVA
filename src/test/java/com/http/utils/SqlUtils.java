package com.http.utils;

import com.cases.LoginCase;
import com.pojo.CaseInfo;
import com.pojo.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作工具类
 */
public class SqlUtils {
    private static Logger logger = Logger.getLogger(SqlUtils.class);

    public static void main(String[] args) throws Exception {
        //新增
       /* String sql = "insert into member(reg_name,pwd,mobile_phone,type,leave_amount,reg_time) " +
                "VALUES ('海贼王','25DJSGDJSJSJA400HSHSGAD','18888888888',1,10,NOW());*/
        //修改
      /*  String sql = "update member set type = 1 where mobile_phone = '18888888888';";
        Update(sql);*/

        //查询
        //MapHandler();
        //BeanHandler();
        //BeanListHandler();

    }

    /**
     * 执行sqi获取单个结果集
     * @param sql  sql语句
     * @return
     * @throws SQLException
     */
    public static Object getSingResult(String sql) {

        if (StringUtils.isBlank(sql)) {
            return null;
        }
        Object result = null;
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            ScalarHandler handler = new ScalarHandler();
            result = runner.query(conn, sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //无论是否发生异常都会执行，一般用来释放资源
            JDBCUtils.close(conn);
        }
        logger.info("数据库断言结果:"+result);
        return result;
    }

    /*private static void BeanListHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        BeanListHandler<Member> handler = new BeanListHandler<>(Member.class);
        String sql = "select * from member limit 5";
        List<Member> result = runner.query(conn, sql, handler);
        for(Member member : result){
            System.out.println(member);
        }
    }

    private static void BeanHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        BeanHandler<Member> handler = new BeanHandler<>(Member.class);
        String sql = "select * from member where mobile_phone = '18888888888';";
        Member result= runner.query(conn, sql, handler);
        System.out.println(result);
    }

    private static void MapHandler() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        MapHandler handler = new MapHandler();
        String sql = "select * from member where mobile_phone = '18888888888';";
        Map<String, Object> result = runner.query(conn, sql, handler);
        System.out.println(result);
    }

    public static void Update(String sql) throws SQLException {
        //DBUtils
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into member(reg_name,pwd,mobile_phone,type,leave_amount,reg_time) " +
                "VALUES ('海贼王','25DJSGDJSJSJA400HSHSGAD','18888888888',1,10,NOW());";
        //可以进行增删改查操作
        runner.update(conn, sql);
        JDBCUtils.close(conn);
    }*/
}

