package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2017/11/30.
 */

public class GoodsModel {
    private int id;
    private String name;
    private long xiaoliang;
    private String imageUrl;

    public GoodsModel(int id, String name, long xiaoliang, String imageUrl) {
        this.id = id;
        this.name = name;
        this.xiaoliang = xiaoliang;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getXiaoliang() {
        return xiaoliang;
    }

    public void setXiaoliang(long xiaoliang) {
        this.xiaoliang = xiaoliang;
    }
}
