package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2017/12/15.
 */

public class AdModel {
    private AdBean ad1;
    private AdBean ad2;
    private AdBean ad3;
    private AdBean ad4;
    private AdBean ad5;

    public AdModel(AdBean ad1, AdBean ad2, AdBean ad3, AdBean ad4, AdBean ad5) {
        this.ad1 = ad1;
        this.ad2 = ad2;
        this.ad3 = ad3;
        this.ad4 = ad4;
        this.ad5 = ad5;
    }

    public AdBean getAd1() {
        return ad1;
    }

    public void setAd1(AdBean ad1) {
        this.ad1 = ad1;
    }

    public AdBean getAd2() {
        return ad2;
    }

    public void setAd2(AdBean ad2) {
        this.ad2 = ad2;
    }

    public AdBean getAd3() {
        return ad3;
    }

    public void setAd3(AdBean ad3) {
        this.ad3 = ad3;
    }

    public AdBean getAd4() {
        return ad4;
    }

    public void setAd4(AdBean ad4) {
        this.ad4 = ad4;
    }

    public AdBean getAd5() {
        return ad5;
    }

    public void setAd5(AdBean ad5) {
        this.ad5 = ad5;
    }
}
