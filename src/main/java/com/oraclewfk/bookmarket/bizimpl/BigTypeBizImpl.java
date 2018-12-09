package com.oraclewfk.bookmarket.bizimpl;

import java.util.List;
import com.oraclewfk.bookmarket.biz.BigTypeBiz;
import com.oraclewfk.bookmarket.dao.BigTypeDao;
import com.oraclewfk.bookmarket.dao.SmallTypeDao;
import com.oraclewfk.bookmarket.daoimpl.BigTypeDaoJdbcImpl;
import com.oraclewfk.bookmarket.daoimpl.SmallTypeDaoJdbcImpl;
import com.oraclewfk.bookmarket.model.BigType;
import com.oraclewfk.bookmarket.model.SmallType;

public class BigTypeBizImpl implements BigTypeBiz {

	@Override
	public boolean save(String name) {
		BigTypeDao userDao=new BigTypeDaoJdbcImpl();
		return userDao.save(name);
	}

	@Override
	public List<BigType> findAllBigType() {
		BigTypeDao userDao=new BigTypeDaoJdbcImpl();
		return userDao.findAll();
	}

	@Override
	public List<SmallType> findAllByType(int bid) {
		SmallTypeDao smallTypeDao=new SmallTypeDaoJdbcImpl();
		return smallTypeDao.findAll(bid);
	}

}
