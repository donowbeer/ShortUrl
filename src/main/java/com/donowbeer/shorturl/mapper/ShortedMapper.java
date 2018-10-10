package com.donowbeer.shorturl.mapper;

import com.donowbeer.shorturl.bean.Shorted;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShortedMapper {

    List<Shorted> selectAll();

    String selectOneSpecifiedByLongUrl(@Param("longUrl") String longUrl);

    String selectOneSpecifiedByKeyword(@Param("keyword") String keyword);

    void insertOne(Shorted shorted);

}
