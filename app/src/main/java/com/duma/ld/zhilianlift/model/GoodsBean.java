package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

public class GoodsBean implements Serializable {
    private int goods_id;
    private String original_img;
    private int cat_id3;
    private String goods_sn;
    private String goods_name;
    private String shop_price;
    private int comment_count;
    private int sales_sum;
    private int is_virtual;
    private int good_comment_rate;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public int getCat_id3() {
        return cat_id3;
    }

    public void setCat_id3(int cat_id3) {
        this.cat_id3 = cat_id3;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getSales_sum() {
        return sales_sum;
    }

    public void setSales_sum(int sales_sum) {
        this.sales_sum = sales_sum;
    }

    public int getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(int is_virtual) {
        this.is_virtual = is_virtual;
    }

    public int getGood_comment_rate() {
        return good_comment_rate;
    }

    public void setGood_comment_rate(int good_comment_rate) {
        this.good_comment_rate = good_comment_rate;
    }
}