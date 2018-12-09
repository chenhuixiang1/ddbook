package com.oraclewfk.bookmarket.bizimpl;

import com.oraclewfk.bookmarket.dao.AdminDao;
import com.oraclewfk.bookmarket.daoimpl.AdminDaoJdbcImpl;
import com.oraclewfk.bookmarket.model.Admin;
import com.oraclewfk.bookmarket.biz.AdminBiz;

public class AdminBizImpl implements AdminBiz {
    @Override
    public boolean findNameAndPwd(Admin admin) {
        AdminDao adminDao=new AdminDaoJdbcImpl();
        return adminDao.findNamePwd(admin);
    }
}
