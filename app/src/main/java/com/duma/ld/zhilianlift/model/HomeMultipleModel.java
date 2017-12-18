package com.duma.ld.zhilianlift.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * type 4个
 * <p>
 * top分类 占2
 * 中间广告 占10
 * goods head 占10
 * goods 占5
 * Created by liudong on 2017/12/15.
 */

public class HomeMultipleModel implements MultiItemEntity {
    public static final int fenlei = 1;
    public static final int ad = 2;
    public static final int goodsHead = 3;
    public static final int goods = 4;

    //必要参数
    private int itemType;
    private int spanSize;

    //中间的广告
    private AdModel adModel;
    //class
    private AdBean classModel;
    //head
    private GoodsAllBean headModel;
    //goods
    private GoodsBean goodsModel;

    public HomeMultipleModel(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public static HomeMultipleModel getClassTypeModel(AdBean adBean) {
        HomeMultipleModel multipleModel = new HomeMultipleModel(fenlei, 2);
        multipleModel.setClassModel(adBean);
        return multipleModel;
    }

    public static HomeMultipleModel getGoodsTypeModel(GoodsBean goodsBean) {
        HomeMultipleModel multipleModel = new HomeMultipleModel(goods, 5);
        multipleModel.setGoodsModel(goodsBean);
        return multipleModel;
    }

    public static HomeMultipleModel getHeadTypeModel(GoodsAllBean goodsAllBean) {
        HomeMultipleModel multipleModel = new HomeMultipleModel(goodsHead, 10);
        multipleModel.setHeadModel(goodsAllBean);
        return multipleModel;
    }

    public static HomeMultipleModel getAdTypeModel(AdModel adModel) {
        HomeMultipleModel multipleModel = new HomeMultipleModel(ad, 10);
        multipleModel.setAdModel(adModel);
        return multipleModel;
    }

    public AdModel getAdModel() {
        return adModel;
    }

    public void setAdModel(AdModel adModel) {
        this.adModel = adModel;
    }

    public AdBean getClassModel() {
        return classModel;
    }

    public void setClassModel(AdBean classModel) {
        this.classModel = classModel;
    }

    public GoodsAllBean getHeadModel() {
        return headModel;
    }

    public void setHeadModel(GoodsAllBean headModel) {
        this.headModel = headModel;
    }

    public GoodsBean getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(GoodsBean goodsModel) {
        this.goodsModel = goodsModel;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}
