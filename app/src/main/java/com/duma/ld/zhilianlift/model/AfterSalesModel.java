package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/12.
 */

public class AfterSalesModel {
    /**
     * goods_id : 129
     * goods_name : 【蚂蚁摄影】Canon/佳能 PowerShot SX410 IS 媲单反长焦数码相机
     * goods_price : 1098.00
     * goods_num : 1
     * original_img : /public/upload/goods/2016/04-21/57187dd92a26f.jpg
     * spec_key :
     * spec_key_name :
     * order_id : 450
     * order_sn : 201801111105132379
     * store_name : 海澜之家
     * store_address : 浙江省杭州市滨江区西丽镇留仙大道1001号
     * service_phone : 0755-86140794
     */
    private int rec_id;
    private int goods_id;
    private String goods_name;
    private double goods_price;
    private int goods_num;
    private String original_img;
    private String spec_key;
    private String spec_key_name;
    private int order_id;
    private String order_sn;
    private String store_name;
    private String store_address;
    private String service_phone;
    /**
     * province : 山西省
     * city : 阳泉市
     * address : 你够
     * total_address : 山西省阳泉市平定县你够
     * consignee : 金素恩尼
     * mobile : 18772397062
     */

    private String province;
    private String city;
    private String address;
    private String total_address;
    private String consignee;
    private String mobile;

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public String getSpec_key_name_noNull() {
        if (spec_key_name == null) {
            return "";
        }
        return spec_key_name;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public String getSpec_key() {
        return spec_key;
    }

    public void setSpec_key(String spec_key) {
        this.spec_key = spec_key;
    }

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getService_phone() {
        return service_phone;
    }

    public void setService_phone(String service_phone) {
        this.service_phone = service_phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal_address() {
        return total_address;
    }

    public void setTotal_address(String total_address) {
        this.total_address = total_address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
