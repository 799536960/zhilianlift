package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2018/1/4.
 */

public class ShoppingSpacModel implements Serializable {
    private String goods_id;
    private int goods_num;
    private int item_id;

    public ShoppingSpacModel(String goods_id, int goods_num) {
        this.goods_id = goods_id;
        this.goods_num = goods_num;
    }

    @Override
    public String toString() {
        return "ShoppingSpacModel{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_num=" + goods_num +
                ", item_id=" + item_id +
                '}';
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
