package com.oraclewfk.bookmarket.biz;

import com.oraclewfk.bookmarket.model.SmallType;

public interface SmallTypeBiz {

    boolean save(SmallType smallType);

    int findBidById(int sid);
}
