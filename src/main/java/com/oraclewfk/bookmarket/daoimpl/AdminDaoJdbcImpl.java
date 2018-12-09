package com.oraclewfk.bookmarket.daoimpl;

import com.oraclewfk.bookmarket.dao.AdminDao;
import com.oraclewfk.bookmarket.model.Admin;
import com.oraclewfk.bookmarket.util.DBUtils;
import com.oraclewfk.bookmarket.util.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoJdbcImpl implements AdminDao {

    @Override
    public boolean findNamePwd(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            //驱动加载Class.forName会引起类加载，类加载会引起静态代码块执行 在静态代码块进行查询
            /*String sql ="select * from t_admin where name=? and pwd=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            stmt.setString(2, MD5Util.getMD5(admin.getPwd()));*/
            //盐在当前用户的密码那一页 我们先获取 密码进行加密 获得盐 拿到盐就加密
            conn =DBUtils.getConnection();
            //获取表中的密码
            String sql ="select pwd from t_admin where name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            rs = stmt.executeQuery();
            if(rs.next()) {
                //得到数据库的密码
                String dbPwd=rs.getString(1);
                //让用户的密码和数据库的密码比对
                return MD5Util.validPasswd(admin.getPwd(),dbPwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        DBUtils.free(rs,stmt, conn);
        }
        return false;
        }
}
