package com.donowbeer.shorturl.service.impl;

import com.donowbeer.shorturl.bean.Viewed;
import com.donowbeer.shorturl.mapper.ViewedMapper;
import com.donowbeer.shorturl.service.ViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewedServiceImpl implements ViewedService {

    @Autowired
    ViewedMapper viewedMapper;


    public void insertOne(Viewed viewed) {
        viewedMapper.insertOne(viewed);
    }

    public int selectCountByKeyword(String keyword) {
        return viewedMapper.selectCountByKeyword(keyword);
    }



}
