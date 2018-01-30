package com.duma.ld.zhilianlift.model;

import org.litepal.crud.DataSupport;

/**
 * Created by liudong on 2018/1/30.
 */

public class ApiCacheModel extends DataSupport {
    private int id;
    private long time;
    private String url;
    private String httpType;
    //参数
    private String canShu;
    private String body;
    //状态码
    private int code;
    //是不是请求 true 是
    private boolean isQinQiu;

    public boolean isQinQiu() {
        return isQinQiu;
    }

    public void setQinQiu(boolean qinQiu) {
        isQinQiu = qinQiu;
    }

    public ApiCacheModel() {
        this.time = System.currentTimeMillis();
    }

    public String getHttpType() {
        return httpType;
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCanShu() {
        return canShu;
    }

    public void setCanShu(String canShu) {
        this.canShu = canShu;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
