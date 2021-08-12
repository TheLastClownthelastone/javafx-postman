package com.pt.sqlite;

import java.sql.*;

/**
 * @author nate-pt
 * @date 2021/8/12 14:45
 * @Since 1.8
 * @Description 使用java链接SQLite
 *  sqlite 数据库轻量级数据库 不需要启动服务 操作具体的.db 文件
 *  遵循sql语法
 */
public class Test {

    /**
     * 测试链接SQLite 数据库
     */
    @org.junit.jupiter.api.Test
    public void exec1(){
        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // 如果没有该文件的话，获取创建该数据库文件，有的话则是解析文件
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\sqlTestdate\\test.db");
            System.out.println("sqlite 数据库创建成功");
            statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    /**
     * 测试sqlite 中创建数据库表
     */
    @org.junit.jupiter.api.Test
    public void exec2() throws SQLException {
        System.out.println("开始链接数据库");
        Connection connection = _get_conn();
        String sql  = "create table company (id int primary key not null" +
                ",name text not null," +
                "age int not null," +
                "address char(50)," +
                "salary real)";

        Statement statement = connection.createStatement();
        statement.execute(sql);
        System.out.println("语句执行成功");
        statement.close();
        connection.close();

    }

    /**
     * 往表中创建数据
     */
    @org.junit.jupiter.api.Test
    public void exec3() throws SQLException {
        Connection c = _get_conn();
        Statement stmt = c.createStatement();
        c.setAutoCommit(false);
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                + "VALUES (1, 'Paul', 32, 'California', 20000.00 );\n");
        sb.append("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );\n");
        sb.append("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );\n");
        sb.append("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
                + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );");
        stmt.executeUpdate(sb.toString());
        c.commit();
    }


    /**
     * 获取sqlite 数据库链接
     * @return
     */
    private Connection  _get_conn(){
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // 如果没有该文件的话，获取创建该数据库文件，有的话则是解析文件
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\sqlTestdate\\test.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
