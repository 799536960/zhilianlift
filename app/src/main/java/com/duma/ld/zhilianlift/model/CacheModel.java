package com.duma.ld.zhilianlift.model;

import org.litepal.crud.DataSupport;

/**
 * Created by liudong on 2017/12/6.
 */

public class CacheModel extends DataSupport {
    private int id;
    private long date;
    private String content;

    public CacheModel(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
