package org.example.dao;

import org.example.util.DBUil;

import java.sql.*;

public class BasicDao {
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(DBUil.DRIVER);
            //3、连接数据库(端口号3306后面是数据库的名字)  127.0.0.1是主机，本地ip地址
            conn = DriverManager.getConnection(DBUil.URL, DBUil.USER, DBUil.PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("注册失败"+e.getMessage());
        } catch (SQLException e) {
            System.out.println("连接数据库失败"+e.getMessage());
        }
        return conn;
    }
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //整合增删改
    public boolean query(String sql,Object...obj){
        Connection conn = getConn();
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            //给问号赋值
            for(int i = 0;i<obj.length;i++) {
                ps.setObject((i+1), obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeAll(conn, ps, null);
        }
        return result > 0 ? true : false;
    }
}
