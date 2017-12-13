package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/12/13.
 */

public class AddresModel implements Serializable{

    /**
     * address_id : 37
     * user_id : 50
     * consignee : 张凯
     * email :
     * country : 0
     * province : 11
     * city : 123
     * district : 1252
     * twon : 0
     * address : 南山区西丽镇留仙大道1001号
     * zipcode :
     * mobile : 17055916591
     * is_default : 1
     * province_name : 浙江省
     * city_name : 杭州市
     * district_name : 西湖区
     * twon_name :
     * province_city_district : 浙江省杭州市西湖区
     */

    private int address_id;
    private int user_id;
    private String consignee;
    private String email;
    private int country;
    private int province;
    private int city;
    private int district;
    private int twon;
    private String address;
    private String zipcode;
    private String mobile;
    private int is_default;
    private String province_name;
    private String city_name;
    private String district_name;
    private String twon_name;
    private String province_city_district;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getTwon() {
        return twon;
    }

    public void setTwon(int twon) {
        this.twon = twon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getTwon_name() {
        return twon_name;
    }

    public void setTwon_name(String twon_name) {
        this.twon_name = twon_name;
    }

    public String getProvince_city_district() {
        return province_city_district;
    }

    public void setProvince_city_district(String province_city_district) {
        this.province_city_district = province_city_district;
    }
}
