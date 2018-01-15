package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/15.
 */

public class AfterSalesInfoModel {
    /**
     * goods_name : 华为 HUAWEI MediaQ M330 华为盒子 4K极清网络机顶盒（黑色）
     * goods_price : 349.00
     * consignee : null
     * mobile : 15209835828
     * get_goods : 0
     * address : null
     * spec_key_name : 颜色:黑色
     * original_img : null
     * goods_num : 1
     * status : 0
     * type : 0
     * id : 45
     * return_code : 201801151329441802
     * reason : null
     * refund_integral : 0
     * refund_deposit : 0.00
     * refund_money : 0.00
     * order_status_context : [{"id":20,"return_goods_id":45,"status":0,"context":"待供应商审核"}]
     */

    private String goods_name;
    private String goods_price;
    private String consignee;
    private String mobile;
    private int get_goods;
    private String address;
    private String spec_key_name;
    private String original_img;
    private int goods_num;
    private int status;
    private int type;
    private int id;
    private String return_code;
    private String reason;
    private double refund_integral;
    private double renovation_money;
    private double refund_money;
    private List<OrderStatusContextBean> order_status_context;


    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getSpec_key_name_noNull() {
        if (spec_key_name == null) {
            return "";
        }
        return spec_key_name;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGet_goods() {
        return get_goods;
    }

    public void setGet_goods(int get_goods) {
        this.get_goods = get_goods;
    }


    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }


    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public double getRefund_integral() {
        return refund_integral;
    }

    public void setRefund_integral(double refund_integral) {
        this.refund_integral = refund_integral;
    }

    public double getRenovation_money() {
        return renovation_money;
    }

    public void setRenovation_money(double renovation_money) {
        this.renovation_money = renovation_money;
    }

    public double getRefund_money() {
        return refund_money;
    }

    public void setRefund_money(double refund_money) {
        this.refund_money = refund_money;
    }

    public List<OrderStatusContextBean> getOrder_status_context() {
        return order_status_context;
    }

    public void setOrder_status_context(List<OrderStatusContextBean> order_status_context) {
        this.order_status_context = order_status_context;
    }

    public static class OrderStatusContextBean {
        /**
         * id : 20
         * return_goods_id : 45
         * status : 0
         * context : 待供应商审核
         */

        private int id;
        private int return_goods_id;
        private int status;
        private String context;
        private long add_time;

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getReturn_goods_id() {
            return return_goods_id;
        }

        public void setReturn_goods_id(int return_goods_id) {
            this.return_goods_id = return_goods_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }
}
