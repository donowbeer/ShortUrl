package com.donowbeer.shorturl.service;

import com.donowbeer.shorturl.bean.Shorted;

import java.util.List;

public interface ShortedService {

    List<Shorted> selectAll();

    String selectOneSpecifiedByLongUrl(String longUrl);

    String selectOneSpecifiedByKeyword(String keyword);

    void insertOne(Shorted shorted);


}
