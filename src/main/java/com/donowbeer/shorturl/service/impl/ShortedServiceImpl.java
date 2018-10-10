package com.donowbeer.shorturl.service.impl;


import com.donowbeer.shorturl.bean.Shorted;
import com.donowbeer.shorturl.mapper.ShortedMapper;
import com.donowbeer.shorturl.service.ShortedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortedServiceImpl implements ShortedService {

    @Autowired
    ShortedMapper shortedMapper;

    public List<Shorted> selectAll() {
        return shortedMapper.selectAll();
    }

    public String selectOneSpecifiedByLongUrl(String longUrl) {
        return shortedMapper.selectOneSpecifiedByLongUrl(longUrl);
    }

    public String selectOneSpecifiedByKeyword(String keyword) {
        return shortedMapper.selectOneSpecifiedByKeyword(keyword);
    }

    public void insertOne(Shorted shorted) {
        shortedMapper.insertOne(shorted);

    }



}
