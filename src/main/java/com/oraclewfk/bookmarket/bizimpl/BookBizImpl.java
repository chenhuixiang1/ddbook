package com.oraclewfk.bookmarket.bizimpl;

import java.util.List;
import com.oraclewfk.bookmarket.biz.BookBiz;
import com.oraclewfk.bookmarket.dao.BookDao;
import com.oraclewfk.bookmarket.daoimpl.BookDaoJdbcImpl;
import com.oraclewfk.bookmarket.model.Book;

public class BookBizImpl implements BookBiz {

	@Override
	public boolean save(Book book) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.save(book);
	}

	@Override
	public List<Book> findAll(int currentPage,String name,int sid) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.findAll(currentPage,name,sid);
	}

	@Override
	public int totalRow(String name,int sid) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.total(name,sid);
	}

	@Override
	public int delId(int id) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.del(id);
	}

	@Override
	public Book findBookId(int id) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.findbigId(id);
	}

	@Override
	public boolean update(Book book) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.update(book);
	}

}
