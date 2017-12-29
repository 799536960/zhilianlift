package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2017/12/29.
 */

public class ShoppingCartSelectModel {

    /**
     * id : 10
     * goods_num : 8
     * selected : 1
     */

    private int id;
    private int goods_num;
    private int selected;
    private int goodsPosition;

    public int getGoodsPosition() {
        return goodsPosition;
    }

    public void setGoodsPosition(int goodsPosition) {
        this.goodsPosition = goodsPosition;
    }

    public ShoppingCartSelectModel(ShoppingCartListModel shoppingCartListModel, boolean selected, int goodsPosition) {
        this.id = shoppingCartListModel.getShoppingCartStoreGoodsModel().getId();
        this.goods_num = shoppingCartListModel.getShoppingCartStoreGoodsModel().getGoods_num();
        this.goodsPosition = goodsPosition;
        if (selected) {
            this.selected = 1;
        } else {
            this.selected = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
