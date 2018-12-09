package com.oraclewfk.bookmarket.bizimpl;

import com.oraclewfk.bookmarket.biz.SmallTypeBiz;
import com.oraclewfk.bookmarket.dao.SmallTypeDao;
import com.oraclewfk.bookmarket.daoimpl.SmallTypeDaoJdbcImpl;
import com.oraclewfk.bookmarket.model.SmallType;

public class SmallTypeBizImpl implements SmallTypeBiz {

	@Override
	public boolean save(SmallType smallType) {
		SmallTypeDao smallTypeDao=new SmallTypeDaoJdbcImpl();
		return smallTypeDao.save(smallType);
	}

	@Override
	public int findBidById(int sid) {
		SmallTypeDao smallTypeDao=new SmallTypeDaoJdbcImpl();
		return smallTypeDao.findBidbyid(sid);
	}

}
