package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/15.
 */

public class AfterSalesListModel {
    /**
     * goods_name : 荣耀畅玩平板note 9.6英寸平板电脑 移动/联通双4G LTE版
     * goods_price : 500.00
     * spec_key_name : null
     * original_img : /public/upload/goods/2016/01-13/5695c0d15fc5f.jpg
     * goods_num : 1
     * status : 0
     * type : 0
     * id : 28
     * return_code : null
     * reason : 操作有误（商品／地址选错）
     * refund_integral : 0
     * refund_deposit : 0.00
     * refund_money : 500.00
     */

    private String goods_name;
    private String goods_price;
    private String spec_key_name;
    private String original_img;
    private int goods_num;
    private int status;
    private int type;
    private int id;
    private String return_code;
    private String reason;
    private String refund_integral;
    private String refund_deposit;
    private String refund_money;
    private String context;


    public String getSpec_key_name_noNull() {
        if (spec_key_name == null) {
            return "";
        }
        return spec_key_name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRefund_integral() {
        return refund_integral;
    }

    public void setRefund_integral(String refund_integral) {
        this.refund_integral = refund_integral;
    }

    public String getRefund_deposit() {
        return refund_deposit;
    }

    public void setRefund_deposit(String refund_deposit) {
        this.refund_deposit = refund_deposit;
    }

    public String getRefund_money() {
        return refund_money;
    }

    public void setRefund_money(String refund_money) {
        this.refund_money = refund_money;
    }
}
