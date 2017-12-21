package com.duma.ld.zhilianlift.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * type 3个
 * head 只有name     1
 * content 有item    2
 * 线 啥都没有        3
 * Created by liudong on 2017/12/21.
 */

public class ScreeningModel implements MultiItemEntity {
    public static final int head = 1;
    public static final int content = 2;
    public static final int view = 3;
    //必要参数
    private int itemType;
    private int spanSize;

    private String name;
    private GoodsListModel.FilterAttrBean.ItemBean itemBean;

    private boolean isChenked;

    public ScreeningModel(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.isChenked = false;
    }

    public static ScreeningModel newHeadModel(String name) {
        ScreeningModel multipleModel = new ScreeningModel(head, 3);
        multipleModel.setName(name);
        return multipleModel;
    }

    public static ScreeningModel newContentModel(GoodsListModel.FilterAttrBean.ItemBean itemBean) {
        ScreeningModel multipleModel = new ScreeningModel(content, 1);
        multipleModel.setItemBean(itemBean);
        return multipleModel;
    }

    public static ScreeningModel newViewModel() {
        return new ScreeningModel(view, 3);
    }

    public boolean isChenked() {
        return isChenked;
    }

    public void setChenked(boolean chenked) {
        isChenked = chenked;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsListModel.FilterAttrBean.ItemBean getItemBean() {
        return itemBean;
    }

    public void setItemBean(GoodsListModel.FilterAttrBean.ItemBean itemBean) {
        this.itemBean = itemBean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }


}
