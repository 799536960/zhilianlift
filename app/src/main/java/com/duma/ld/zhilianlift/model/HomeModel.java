package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/1.
 */

public class HomeModel {
    private AdBean ad1;
    private AdBean ad2;
    private AdBean ad3;
    private AdBean ad4;
    private AdBean ad5;
    private List<GoodsAllBean> GoodsAll;
    private List<AdBean> lunbo;
    private List<AdBean> goodsCategoryList;

    public AdBean getAd1() {
        return ad1;
    }

    public void setAd1(AdBean ad1) {
        this.ad1 = ad1;
    }

    public AdBean getAd2() {
        return ad2;
    }

    public void setAd2(AdBean ad2) {
        this.ad2 = ad2;
    }

    public AdBean getAd3() {
        return ad3;
    }

    public void setAd3(AdBean ad3) {
        this.ad3 = ad3;
    }

    public AdBean getAd4() {
        return ad4;
    }

    public void setAd4(AdBean ad4) {
        this.ad4 = ad4;
    }

    public AdBean getAd5() {
        return ad5;
    }

    public void setAd5(AdBean ad5) {
        this.ad5 = ad5;
    }

    public List<GoodsAllBean> getGoodsAll() {
        return GoodsAll;
    }

    public void setGoodsAll(List<GoodsAllBean> goodsAll) {
        GoodsAll = goodsAll;
    }

    public List<AdBean> getLunbo() {
        return lunbo;
    }

    public void setLunbo(List<AdBean> lunbo) {
        this.lunbo = lunbo;
    }

    public List<AdBean> getGoodsCategoryList() {
        return goodsCategoryList;
    }

    public void setGoodsCategoryList(List<AdBean> goodsCategoryList) {
        this.goodsCategoryList = goodsCategoryList;
    }
}
