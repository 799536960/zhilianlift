package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2017/11/30.
 */

public class AdModel {
    private int id;
    /**
     * type=1 商品 goodsModel不能为空
     * type=2 分类 id title imgUrl
     * type=3 网页h5 url title  imgUrl不能为空
     */
    private String imgUrl;
    private int type;
    private String url;
    private String title;
    private GoodsModel goodsModel;

    public static AdModel getGoods(GoodsModel goodsModel) {
        AdModel adModel = new AdModel();
        adModel.setType(1);
        adModel.setGoodsModel(goodsModel);
        return adModel;
    }

    public static AdModel getClass(int id, String title, String imgUrl) {
        AdModel adModel = new AdModel();
        adModel.setType(2);
        adModel.setId(id);
        adModel.setTitle(title);
        adModel.setImgUrl(imgUrl);
        return adModel;
    }

    public static AdModel getH5(String url, String title, String imgUrl) {
        AdModel adModel = new AdModel();
        adModel.setType(3);
        adModel.setUrl(url);
        adModel.setTitle(title);
        adModel.setImgUrl(imgUrl);
        return adModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GoodsModel getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(GoodsModel goodsModel) {
        this.goodsModel = goodsModel;
    }
}
