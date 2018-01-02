package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/2.
 */

public class ShoppingChangeNumModel {
    private ShoppingCartModel.TotalPriceBean total_price;
    private ShoppingCartStoreGoodsModel cart;

    public ShoppingCartModel.TotalPriceBean getTotal_price() {
        return total_price;
    }

    public void setTotal_price(ShoppingCartModel.TotalPriceBean total_price) {
        this.total_price = total_price;
    }

    public ShoppingCartStoreGoodsModel getCart() {
        return cart;
    }

    public void setCart(ShoppingCartStoreGoodsModel cart) {
        this.cart = cart;
    }
}
