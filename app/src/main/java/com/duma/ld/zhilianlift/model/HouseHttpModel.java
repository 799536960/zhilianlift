package com.duma.ld.zhilianlift.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2018/1/16.
 */

public class HouseHttpModel implements Serializable {
    @Override
    public String toString() {
        return "HouseHttpModel{" +
                "isRental=" + isRental +
                ", mListTeSe=" + mListTeSe +
                ", mListLeiXin=" + mListLeiXin +
                ", mListSheShi=" + mListSheShi +
                '}';
    }

    //是否出租房
    private boolean isRental;
    //房屋特色 出售才会出现
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListTeSe;
    //物业类型
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListLeiXin;
    //房屋设施  出租才会出现
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListSheShi;


    public List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getmListSheShi() {
        return mListSheShi;
    }

    public void setmListSheShi(List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListSheShi) {
        this.mListSheShi = mListSheShi;
    }

    public List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getmListLeiXin() {
        return mListLeiXin;
    }

    public void setmListLeiXin(List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListLeiXin) {
        this.mListLeiXin = mListLeiXin;
    }

    public List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getmListTeSe() {
        return mListTeSe;
    }

    public void setmListTeSe(List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListTeSe) {
        this.mListTeSe = mListTeSe;
    }

    public boolean isRental() {
        return isRental;
    }

    public void setRental(boolean rental) {
        isRental = rental;
    }


}
