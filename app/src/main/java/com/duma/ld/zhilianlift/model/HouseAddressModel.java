package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2018/5/11.
 */

public class HouseAddressModel implements Serializable {
    private String address;
    private int province_id;
    private int city_id;
    private int district;

    public HouseAddressModel(String address, int province_id, int city_id, int district) {
        this.address = address;
        this.province_id = province_id;
        this.city_id = city_id;
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }
}
