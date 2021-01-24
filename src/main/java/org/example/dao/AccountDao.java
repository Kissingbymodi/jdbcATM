package org.example.dao;


import org.example.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AccountDao extends BasicDao{
    public List<Account> find(String sql, Object...obj){
        List<Account> list = new ArrayList<Account>();
        Connection conn = BasicDao.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0;i<obj.length;i++) {
                ps.setObject(i+1, obj[i]);
            }
            rs = ps.executeQuery();
            while(rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setTel(rs.getString(3));
                a.setCardNumber(rs.getString(4));
                a.setPassword(rs.getString(5));
                a.setCardType(rs.getString(6));
                a.setBalance(rs.getDouble(7));
                a.setAddTime(rs.getString(8));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("sql异常"+e.getMessage());
        }finally {
            BasicDao.closeAll(conn, ps, rs);
        }
        return list;
    }
}
