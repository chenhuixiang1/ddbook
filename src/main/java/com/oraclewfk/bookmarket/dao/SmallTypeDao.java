package com.oraclewfk.bookmarket.dao;

import java.util.List;

import com.oraclewfk.bookmarket.model.SmallType;

public interface SmallTypeDao {

	boolean save(SmallType smallType);

	List<SmallType> findAll(int bid);

    int findBidbyid(int sid);
}
