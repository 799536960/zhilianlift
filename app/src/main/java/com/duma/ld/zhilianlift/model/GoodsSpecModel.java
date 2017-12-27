package com.duma.ld.zhilianlift.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 商品规格选择
 * Created by liudong on 2017/12/26.
 */

public class GoodsSpecModel implements MultiItemEntity {
    public static final int head = 0;
    public static final int content = 1;
    public static final int footer = 2;
    //必要参数
    private int itemType;
    private int spanSize;
    private String name;
    private GoodsSpecListBean.SpecListBean specListBean;

    public static GoodsSpecModel newHead(String name) {
        GoodsSpecModel model = new GoodsSpecModel(head, 4);
        model.setName(name);
        return model;
    }

    public static GoodsSpecModel newFooter() {
        GoodsSpecModel model = new GoodsSpecModel(footer, 4);
        return model;
    }

    public static GoodsSpecModel newContent(GoodsSpecListBean.SpecListBean specListBean, boolean isSelect, int upPosition, int thisPosition) {
        specListBean.setSelect(isSelect);
        specListBean.setUpPosition(upPosition);
        specListBean.setThisPosition(thisPosition);
        GoodsSpecModel model = new GoodsSpecModel(content, 1);
        model.setSpecListBean(specListBean);
        return model;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsSpecListBean.SpecListBean getSpecListBean() {
        return specListBean;
    }

    public void setSpecListBean(GoodsSpecListBean.SpecListBean specListBean) {
        this.specListBean = specListBean;
    }

    public GoodsSpecModel(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
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

    @Override
    public int getItemType() {
        return itemType;
    }
}
