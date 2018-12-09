package com.oraclewfk.bookmarket.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oraclewfk.bookmarket.dao.BigTypeDao;
import com.oraclewfk.bookmarket.model.BigType;
import com.oraclewfk.bookmarket.util.DBUtils;

public class BigTypeDaoJdbcImpl implements BigTypeDao {

	@Override
	public boolean save(String name) {
		Connection conn = null; 
		PreparedStatement stmt=null;
		try {
			conn =DBUtils.getConnection();
			String sql ="insert into t_big_type(name) values(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,name);
			int ret = stmt.executeUpdate();
			if(ret>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.free(stmt, conn);
		}
		return false;
	}

	
	@Override
	public List<BigType> findAll() {
		Connection conn = null; 
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			conn =DBUtils.getConnection();
			String sql ="select * from t_big_type";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<BigType> ls=new ArrayList<>();
			while(rs.next()) {
				BigType bigType=new BigType();
				bigType.setId(rs.getInt("id"));
				bigType.setName(rs.getString("name"));
				ls.add(bigType);
			}
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.free(rs, ps, conn);
		}
		return null;
	}

}
