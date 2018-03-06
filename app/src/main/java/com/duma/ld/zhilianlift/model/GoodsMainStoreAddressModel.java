package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/3/6.
 */

public class GoodsMainStoreAddressModel {

    /**
     * id : 30
     * store_id : 3
     * province : 11
     * city : 123
     * district : 1253
     * area : 第一个建材城，第二个建材城
     * province_code : 330000
     * city_code : 330100
     * district_code : 330108
     * province_name : 浙江省
     * city_name : 杭州市
     * district_name : 滨江区
     */

    private int id;
    private String store_id;
    private String province;
    private String city;
    private String district;
    private String area;
    private String province_code;
    private String city_code;
    private String district_code;
    private String province_name;
    private String city_name;
    private String district_name;

    public String getAddress() {
        return getProvince_name() + getCity_name() + getDistrict_name() + getArea();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        if (area == null) {
            return "";
        }
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getProvince_name() {
        if (province_name == null) {
            return "";
        }
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        if (city_name == null) {
            return "";
        }
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        if (district_name == null) {
            return "";
        }
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
}
