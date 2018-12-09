package com.oraclewfk.bookmarket.dao;

import java.util.List;

import com.oraclewfk.bookmarket.model.Book;

public interface BookDao {

	boolean save(Book book);

	List<Book> findAll(int currentPage, String name, int sid);

	int total(String name, int sid);

	int del(int id);

    Book findbigId(int id);

	boolean update(Book book);
}
