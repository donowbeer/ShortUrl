package com.donowbeer.shorturl.bean;

import java.sql.Timestamp;

public class Shorted {

    private String keyword;
    private String longUrl;
    private int type;
    private Timestamp createTime;

    //自定义长度
    private int len;

    public int getLen() {

        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
