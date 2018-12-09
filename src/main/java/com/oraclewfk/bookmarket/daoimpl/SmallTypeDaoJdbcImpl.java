package com.oraclewfk.bookmarket.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oraclewfk.bookmarket.dao.SmallTypeDao;
import com.oraclewfk.bookmarket.model.SmallType;
import com.oraclewfk.bookmarket.util.DBUtils;

public class SmallTypeDaoJdbcImpl implements SmallTypeDao {

	@Override
	public boolean save(SmallType smallType) {
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DBUtils.getConnection();
			stmt = conn.prepareStatement("insert into t_small_type values(default,?,?)");
			stmt.setString(1, smallType.getName());
			stmt.setInt(2, smallType.getBid());
			int ret=stmt.executeUpdate();
			if(ret>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.free(stmt, conn);
		}
		return false;
	}

	@Override
	public List<SmallType> findAll(int bid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			stmt=conn.createStatement();
			String sql="select * from t_small_type where bid="+bid;
			rs=stmt.executeQuery(sql);
			List<SmallType> ls=new ArrayList<>();
			while(rs.next()) {
				SmallType smallType=new SmallType();
				smallType.setId(rs.getInt("id"));
				smallType.setName(rs.getString("name"));
				smallType.setBid(rs.getInt("bid"));
				ls.add(smallType);
			}
			return ls;
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			DBUtils.free(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public int findBidbyid(int sid) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getConnection();
			stmt=conn.createStatement();
			String sql="select bid from t_small_type where id="+sid;
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.free(rs, stmt, conn);
		}
		return 0;
	}
}
