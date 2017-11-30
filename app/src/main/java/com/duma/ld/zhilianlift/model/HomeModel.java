package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/11/30.
 */

public class HomeModel {
    private List<AdModel> LunBoTuList;
    private List<AdModel> FenleiList;
    private AdModel adModel1;
    private AdModel adModel2;
    private AdModel adModel3;
    private AdModel adModel4;
    private AdModel adModel5;
    private List<AdClassModel> adClassModels;

    public HomeModel(List<AdModel> lunBoTuList, List<AdModel> fenleiList, AdModel adModel1, AdModel adModel2, AdModel adModel3, AdModel adModel4, AdModel adModel5, List<AdClassModel> adClassModels) {
        LunBoTuList = lunBoTuList;
        FenleiList = fenleiList;
        this.adModel1 = adModel1;
        this.adModel2 = adModel2;
        this.adModel3 = adModel3;
        this.adModel4 = adModel4;
        this.adModel5 = adModel5;
        this.adClassModels = adClassModels;
    }

    public List<AdModel> getLunBoTuList() {
        return LunBoTuList;
    }

    public void setLunBoTuList(List<AdModel> lunBoTuList) {
        LunBoTuList = lunBoTuList;
    }

    public List<AdModel> getFenleiList() {
        return FenleiList;
    }

    public void setFenleiList(List<AdModel> fenleiList) {
        FenleiList = fenleiList;
    }

    public AdModel getAdModel1() {
        return adModel1;
    }

    public void setAdModel1(AdModel adModel1) {
        this.adModel1 = adModel1;
    }

    public AdModel getAdModel2() {
        return adModel2;
    }

    public void setAdModel2(AdModel adModel2) {
        this.adModel2 = adModel2;
    }

    public AdModel getAdModel3() {
        return adModel3;
    }

    public void setAdModel3(AdModel adModel3) {
        this.adModel3 = adModel3;
    }

    public AdModel getAdModel4() {
        return adModel4;
    }

    public void setAdModel4(AdModel adModel4) {
        this.adModel4 = adModel4;
    }

    public AdModel getAdModel5() {
        return adModel5;
    }

    public void setAdModel5(AdModel adModel5) {
        this.adModel5 = adModel5;
    }

    public List<AdClassModel> getAdClassModels() {
        return adClassModels;
    }

    public void setAdClassModels(List<AdClassModel> adClassModels) {
        this.adClassModels = adClassModels;
    }
}
