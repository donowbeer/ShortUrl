package com.donowbeer.shorturl.service;

import com.donowbeer.shorturl.bean.Viewed;

public interface ViewedService {

    void insertOne(Viewed viewed);

    int selectCountByKeyword(String keyword);

}
