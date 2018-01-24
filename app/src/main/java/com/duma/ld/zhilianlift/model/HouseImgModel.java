package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2018/1/24.
 */

public class HouseImgModel implements Serializable{

    /**
     * id : 169
     * house_id : 86
     * image_url : /public/upload/goods/2018/01-24/bbd20dece2a86e8a551ecd39fa2b8290.jpg
     * img_sort : 0
     * title : null
     * type : 4
     */

    private int id;
    private int house_id;
    private String image_url;
    private int img_sort;
    private String title;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getImg_sort() {
        return img_sort;
    }

    public void setImg_sort(int img_sort) {
        this.img_sort = img_sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
