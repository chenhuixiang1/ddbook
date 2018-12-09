package com.oraclewfk.bookmarket.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.oraclewfk.bookmarket.dao.BookDao;
import com.oraclewfk.bookmarket.model.Book;
import com.oraclewfk.bookmarket.util.DBUtils;
import com.oraclewfk.bookmarket.util.PageContants;

public class BookDaoJdbcImpl implements BookDao {

	@Override
	public boolean save(Book book) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "insert into t_book(name,price,author,publish,publishDate,description,sid,photo) values(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getName());
			stmt.setDouble(2, book.getPrice());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getPublish());
			stmt.setDate(5, new java.sql.Date(book.getPublishDate().getTime()));
			stmt.setString(6, book.getDescription());
			stmt.setInt(7, book.getSid());
			stmt.setString(8,book.getPhoto());
			int ret = stmt.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.free(stmt, conn);
		}
		return false;
	}

	@Override
	public List<Book> findAll(int currentPage,String name,int sid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			// 得到currentPage-1前面多少行页 乘以每页的大小 +1（当前第一页的行数 ）","+PageContants.PAGE_SIZE 得到当前页
			//String sql = "select * from t_book order by id desc limit " + ((currentPage - 1) * PageContants.PAGE_SIZE + 1 - 1) + ","+ PageContants.PAGE_SIZE;
            String sql="select * from t_book where 1=1";
            if(name!=null&&!name.equals("")){
            	/*sql+=" and name like '%+name+%' ";*/
            	sql+=" and name like '%"+name+"%' ";
            }
            if(sid!=-1) {
            	sql+=" and sid="+sid;
            }
            //获取的让它降序排列页数
            sql+= " order by id desc limit " + ((currentPage - 1) * PageContants.PAGE_SIZE + 1 - 1) + ","+ PageContants.PAGE_SIZE;
			rs = stmt.executeQuery(sql);
			List<Book> ls = new ArrayList<>();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getDouble("price"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishDate(rs.getDate("publishDate"));
				book.setDescription(rs.getString("description"));
				book.setSid(rs.getInt("sid"));
				book.setPhoto(rs.getString("photo"));
				ls.add(book);
			}
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.free(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public int total(String name,int sid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			// 得到currentPage-1前面多少行页 乘以每页的大小 +1（当前第一页的行数 ）","+PageContants.PAGE_SIZE 得到当前页
			String sql = "select count(*) from t_book where 1=1";
	            if(name!=null&&!name.equals("")){
	            	sql+=" and name like '%"+name+"%'";
	            }
	            if(sid!=-1) {
	            	sql+=" and sid="+sid;
	            }
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.free(rs, stmt, conn);
		}
		return 0;
	}

	@Override
	public int del(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "delete from t_book where id="+id;
			stmt = conn.prepareStatement(sql);
			int rs=stmt.executeUpdate();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.free(stmt, conn);
		}
		return 0;
	}
	@Override
	public Book findbigId(int id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			// 得到currentPage-1前面多少行页 乘以每页的大小 +1（当前第一页的行数 ）","+PageContants.PAGE_SIZE 得到当前
			String sql = "select * from t_book where id="+id;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getDouble("price"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishDate(rs.getDate("publishDate"));
				book.setDescription(rs.getString("description"));
				book.setSid(rs.getInt("sid"));
				book.setPhoto(rs.getString("photo"));
				return book;
			}
			} catch(Exception e){
				e.printStackTrace();
			} finally{
				DBUtils.free(rs,stmt,conn);
			}
			return null;
		}

	@Override
	public boolean update(Book book) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtils.getConnection();
			if(book.getPhoto()==null){
				stmt = conn.prepareStatement("update t_book set name=?,price=?,author=?,publish=?,publishDate=?,description=?,sid=? where id=?");
				stmt.setString(1, book.getName());
				stmt.setDouble(2, book.getPrice());
				stmt.setString(3, book.getAuthor());
				stmt.setString(4, book.getPublish());
				stmt.setDate(5, new java.sql.Date(book.getPublishDate().getTime()));
				stmt.setString(6, book.getDescription());
				stmt.setInt(7,book.getSid());
				stmt.setInt(8,book.getId());
			}else{
				String sql = "update t_book set name=?,price=?,author=?,publish=?,publishDate=?,description=?,sid=?,photo=? where id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, book.getName());
				stmt.setDouble(2, book.getPrice());
				stmt.setString(3, book.getAuthor());
				stmt.setString(4, book.getPublish());
				stmt.setDate(5, new java.sql.Date(book.getPublishDate().getTime()));
				stmt.setString(6, book.getDescription());
				stmt.setInt(7, book.getSid());
				stmt.setString(8,book.getPhoto());
				stmt.setInt(9,book.getId());
			}
			int ret = stmt.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.free(stmt, conn);
		}
		return false;
	}
}
