package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/12/13.
 */

public class PCDAddresModel implements Serializable {
    private ProvinceModel provinceModel;
    private ProvinceModel cityModel;
    private ProvinceModel districtModel;

    public ProvinceModel getProvinceModel() {
        return provinceModel;
    }

    public void setProvinceModel(ProvinceModel provinceModel) {
        this.provinceModel = provinceModel;
    }

    public ProvinceModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(ProvinceModel cityModel) {
        this.cityModel = cityModel;
    }

    public ProvinceModel getDistrictModel() {
        return districtModel;
    }

    public void setDistrictModel(ProvinceModel districtModel) {
        this.districtModel = districtModel;
    }
}
