package com.oraclewfk.bookmarket.biz;

import java.util.List;

import com.oraclewfk.bookmarket.model.BigType;
import com.oraclewfk.bookmarket.model.SmallType;

public interface BigTypeBiz {

	boolean save(String name);
	List<BigType> findAllBigType();
	List<SmallType> findAllByType(int bid);

}
