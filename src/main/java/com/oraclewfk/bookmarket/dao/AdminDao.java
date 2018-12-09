package com.oraclewfk.bookmarket.dao;

import com.oraclewfk.bookmarket.model.Admin;

public interface AdminDao {

    boolean findNamePwd(Admin admin);
}
