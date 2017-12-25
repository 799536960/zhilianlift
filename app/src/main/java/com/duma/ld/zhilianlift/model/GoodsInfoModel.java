package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/25.
 */

public class GoodsInfoModel {

    /**
     * goods_content :
     * goods_attr_list : [{"goods_attr_id":901,"goods_id":121,"attr_id":321,"attr_value":"科智 50000","attr_price":"0","attr_name":"产品名称"},{"goods_attr_id":902,"goods_id":121,"attr_id":322,"attr_value":"科智","attr_price":"0","attr_name":"产品"},{"goods_attr_id":903,"goods_id":121,"attr_id":323,"attr_value":"20000mAh","attr_price":"0","attr_name":"电池容量"}]
     */

    private String goods_content;
    private List<GoodsAttrListBean> goods_attr_list;

    public String getGoods_content() {
        return goods_content;
    }

    public void setGoods_content(String goods_content) {
        this.goods_content = goods_content;
    }

    public List<GoodsAttrListBean> getGoods_attr_list() {
        return goods_attr_list;
    }

    public void setGoods_attr_list(List<GoodsAttrListBean> goods_attr_list) {
        this.goods_attr_list = goods_attr_list;
    }

    public static class GoodsAttrListBean {
        /**
         * goods_attr_id : 901
         * goods_id : 121
         * attr_id : 321
         * attr_value : 科智 50000
         * attr_price : 0
         * attr_name : 产品名称
         */

        private int goods_attr_id;
        private int goods_id;
        private int attr_id;
        private String attr_value;
        private String attr_price;
        private String attr_name;

        public int getGoods_attr_id() {
            return goods_attr_id;
        }

        public void setGoods_attr_id(int goods_attr_id) {
            this.goods_attr_id = goods_attr_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getAttr_id() {
            return attr_id;
        }

        public void setAttr_id(int attr_id) {
            this.attr_id = attr_id;
        }

        public String getAttr_value() {
            return attr_value;
        }

        public void setAttr_value(String attr_value) {
            this.attr_value = attr_value;
        }

        public String getAttr_price() {
            return attr_price;
        }

        public void setAttr_price(String attr_price) {
            this.attr_price = attr_price;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }
    }
}
