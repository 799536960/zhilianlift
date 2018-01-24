package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

public class AdBean implements Serializable {
    /**
     * title : 广告5
     * img_url : /public/upload/ad/2017/11-30/a22852227d812b8fbf2b268ff4956f5d.png
     * url :
     * type : 1
     * goods_id : 135
     * goods : {"goods_id":135,"goods_name":"重庆电信手机卡电话卡语音卡选靓号3G4G卡内部5折卡低资费（飞）","shop_price":"60.00"}
     */

    private String title;
    private String img_url;
    private String url;
    private int type;
    private int goods_id;
    private GoodsBean goods;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }


}