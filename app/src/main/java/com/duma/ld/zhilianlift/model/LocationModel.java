package com.duma.ld.zhilianlift.model;

import com.duma.ld.zhilianlift.util.Constants;
import com.google.gson.Gson;

/**
 * Created by liudong on 2017/12/19.
 */

public class LocationModel {
    private double mLatitude;
    private double mLongitude;
    private String province;
    private String city;
    private String district;
    private String code;

    @Override
    public String toString() {
        return "LocationModel{" +
                "mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public LocationModel(String city, String district, String code) {
        this.city = city;
        this.district = district;
        this.code = code;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static LocationModel getJsonModel(String json) {
        LocationModel model = new Gson().fromJson(json, LocationModel.class);
        if (model == null) {
            return new LocationModel(Constants.defaultCity, "", Constants.defaultCity_code);
        } else {
            return model;
        }
    }
}
