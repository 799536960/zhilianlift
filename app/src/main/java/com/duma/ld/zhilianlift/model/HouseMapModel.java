package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * 地图
 * Created by liudong on 2018/1/23.
 */

public class HouseMapModel implements Serializable {
    private String louPanMinCheng;
    private String address;
    public double latitude;
    public double longitude;

    public HouseMapModel(String louPanMinCheng, String address, double latitude, double longitude) {
        this.louPanMinCheng = louPanMinCheng;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLouPanMinCheng() {
        return louPanMinCheng;
    }

    public void setLouPanMinCheng(String louPanMinCheng) {
        this.louPanMinCheng = louPanMinCheng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
