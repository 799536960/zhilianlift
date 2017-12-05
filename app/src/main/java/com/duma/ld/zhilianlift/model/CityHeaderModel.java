package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/5.
 */

public class CityHeaderModel {
    private boolean isOne;
    private List<QuModel> city;

    public CityHeaderModel(boolean isOne, List<QuModel> city) {
        this.isOne = isOne;
        this.city = city;
    }

    public boolean isOne() {
        return isOne;
    }

    public void setOne(boolean one) {
        isOne = one;
    }

    public List<QuModel> getCity() {
        return city;
    }

    public void setCity(List<QuModel> city) {
        this.city = city;
    }
}
