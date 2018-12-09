package com.oraclewfk.bookmarket.dao;

import java.util.List;

import com.oraclewfk.bookmarket.model.BigType;

public interface BigTypeDao {

	boolean save(String name);

	List<BigType> findAll();

}
