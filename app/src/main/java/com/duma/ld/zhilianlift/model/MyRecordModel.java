package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/3.
 */

public class MyRecordModel {

    /**
     * date : 01月02日
     * visit : [{"goods_id":0,"user_id":50,"visittime":1514887446,"cat_id1":0,"cat_id2":0,"cat_id3":0,"visit_id":174,"type":1,"house_id":1,"goods_name":"金润科技园","goods_price":"12万","original_img":null},{"goods_id":71,"user_id":50,"visittime":1514887446,"cat_id1":4,"cat_id2":37,"cat_id3":301,"visit_id":173,"type":0,"house_id":0,"goods_name":"布雷尔 床 皮床 双人床 真皮床 软床 皮艺床 1.8米婚床","goods_price":"1580.00","original_img":"/public/upload/goods/2016/01-15/5698b4bfd0d36.jpg"},{"goods_id":56,"user_id":50,"visittime":1514881236,"cat_id1":2,"cat_id2":20,"cat_id3":130,"visit_id":33,"type":0,"house_id":0,"goods_name":"创维（skyworth）55M5 55英寸4K超高清网络智能液晶电视机","goods_price":"3699.00","original_img":"/public/upload/goods/2016/01-14/56970fc50a9f3.jpg"},{"goods_id":65,"user_id":50,"visittime":1514880922,"cat_id1":1,"cat_id2":12,"cat_id3":100,"visit_id":24,"type":0,"house_id":0,"goods_name":"长虹(CHANGHONG) 49A1U 49英寸双64位4K超清智能网络LED液晶电视","goods_price":"2799.00","original_img":"/public/upload/goods/2016/01-15/569856c42e7b7.jpg"}]
     */

    private String date;
    private List<VisitBean> visit;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<VisitBean> getVisit() {
        return visit;
    }

    public void setVisit(List<VisitBean> visit) {
        this.visit = visit;
    }

    public static class VisitBean {
        /**
         * goods_id : 0
         * user_id : 50
         * visittime : 1514887446
         * cat_id1 : 0
         * cat_id2 : 0
         * cat_id3 : 0
         * visit_id : 174
         * type : 1
         * house_id : 1
         * goods_name : 金润科技园
         * goods_price : 12万
         * original_img : null
         */

        private int goods_id;
        private int user_id;
        private int visittime;
        private int cat_id1;
        private int cat_id2;
        private int cat_id3;
        private int visit_id;
        private int type;
        private int house_id;
        private String goods_name;
        private String goods_price;
        private String original_img;
        private int collect_id;
        private boolean select;

        public int getCollect_id() {
            return collect_id;
        }

        public void setCollect_id(int collect_id) {
            this.collect_id = collect_id;
        }

        public VisitBean() {
            select = false;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getVisittime() {
            return visittime;
        }

        public void setVisittime(int visittime) {
            this.visittime = visittime;
        }

        public int getCat_id1() {
            return cat_id1;
        }

        public void setCat_id1(int cat_id1) {
            this.cat_id1 = cat_id1;
        }

        public int getCat_id2() {
            return cat_id2;
        }

        public void setCat_id2(int cat_id2) {
            this.cat_id2 = cat_id2;
        }

        public int getCat_id3() {
            return cat_id3;
        }

        public void setCat_id3(int cat_id3) {
            this.cat_id3 = cat_id3;
        }

        public int getVisit_id() {
            return visit_id;
        }

        public void setVisit_id(int visit_id) {
            this.visit_id = visit_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }
    }
}
