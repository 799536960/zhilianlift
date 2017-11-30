package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/11/30.
 */

public class AdClassModel {
    private AdModel adModel;
    private List<GoodsModel> list;

    public AdClassModel(AdModel adModel, List<GoodsModel> list) {
        this.adModel = adModel;
        this.list = list;
    }

    public AdModel getAdModel() {
        return adModel;
    }

    public void setAdModel(AdModel adModel) {
        this.adModel = adModel;
    }

    public List<GoodsModel> getList() {
        return list;
    }

    public void setList(List<GoodsModel> list) {
        this.list = list;
    }
}
