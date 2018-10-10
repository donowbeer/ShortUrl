package com.donowbeer.shorturl.mapper;

import com.donowbeer.shorturl.bean.Viewed;
import org.apache.ibatis.annotations.Param;

public interface ViewedMapper {

    void insertOne(Viewed viewed);

    int selectCountByKeyword(@Param("keyword") String keyword);

}
