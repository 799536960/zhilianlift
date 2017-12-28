package com.duma.ld.zhilianlift.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by liudong on 2017/12/28.
 */

public class ShoppingCartListModel implements MultiItemEntity {
    public static final int head = 1;
    public static final int goods = 2;
    //必要参数
    private int itemType;
    private String storeName;
    private int storeId;

    private ShoppingCartStoreGoodsModel shoppingCartStoreGoodsModel;

    public static ShoppingCartListModel newHeadModel(ShoppingCartStoreModel shoppingCartStoreModel) {
        ShoppingCartListModel model1 = new ShoppingCartListModel(head);
        model1.setStoreId(shoppingCartStoreModel.getStore_id());
        model1.setStoreName(shoppingCartStoreModel.getStore_name());
        return model1;
    }

    public static ShoppingCartListModel newGoodsModel(ShoppingCartStoreGoodsModel shoppingCartStoreGoodsModel) {
        ShoppingCartListModel model1 = new ShoppingCartListModel(goods);
        model1.setShoppingCartStoreGoodsModel(shoppingCartStoreGoodsModel);
        return model1;
    }

    public ShoppingCartListModel(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public ShoppingCartStoreGoodsModel getShoppingCartStoreGoodsModel() {
        return shoppingCartStoreGoodsModel;
    }

    public void setShoppingCartStoreGoodsModel(ShoppingCartStoreGoodsModel shoppingCartStoreGoodsModel) {
        this.shoppingCartStoreGoodsModel = shoppingCartStoreGoodsModel;
    }
}
