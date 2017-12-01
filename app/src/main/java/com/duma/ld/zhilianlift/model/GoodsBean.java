package com.duma.ld.zhilianlift.model;

public class GoodsBean {

    /**
     * goods_id : 161
     * goods_name : 手机
     * shop_price : 100.00
     * cat_id3 : 850
     * original_img : /public/upload/goods/thumb/49/goods_sub_thumb_615_236_236.jpeg
     */

    private int goods_id;
    private String goods_name;
    private String shop_price;
    private int cat_id3;
    private String original_img;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
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

    public int getCat_id3() {
        return cat_id3;
    }

    public void setCat_id3(int cat_id3) {
        this.cat_id3 = cat_id3;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }
}