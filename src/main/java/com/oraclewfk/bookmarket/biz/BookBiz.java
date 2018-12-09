package com.oraclewfk.bookmarket.biz;

import java.util.List;

import com.oraclewfk.bookmarket.model.Book;

public interface BookBiz {

	boolean save(Book book);

	List<Book> findAll(int currentPage, String name, int sid);

	int totalRow(String name, int sid);

	int delId(int id);

	Book findBookId(int id);

	boolean update(Book book);
}

