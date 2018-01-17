package com.duma.ld.zhilianlift.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2018/1/16.
 */

public class HouseHttpModel implements Serializable {
    //是否出租房
    private boolean isRental;
    //房屋特色 出售才会出现
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListTeSe;
    //房屋设施  出租才会出现
    private List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListSheShi;
    //物业类型
    private HouseHttpInfoModel.FilterAttrBean.ItemBean wuYeLeiXinModel;
    //付款方式
    private HouseHttpInfoModel.FilterAttrBean.ItemBean fuKuanFangShiModel;

    public HouseHttpInfoModel.FilterAttrBean.ItemBean getWuYeLeiXinModel() {
        return wuYeLeiXinModel;
    }

    public void setWuYeLeiXinModel(HouseHttpInfoModel.FilterAttrBean.ItemBean wuYeLeiXinModel) {
        this.wuYeLeiXinModel = wuYeLeiXinModel;
    }

    public HouseHttpInfoModel.FilterAttrBean.ItemBean getFuKuanFangShiModel() {
        return fuKuanFangShiModel;
    }

    public void setFuKuanFangShiModel(HouseHttpInfoModel.FilterAttrBean.ItemBean fuKuanFangShiModel) {
        this.fuKuanFangShiModel = fuKuanFangShiModel;
    }

    public List<HouseHttpInfoModel.FilterAttrBean.ItemBean> getmListSheShi() {
        return mListSheShi;
    }

    public void setmListSheShi(List<HouseHttpInfoModel.FilterAttrBean.ItemBean> mListSheShi) {
        this.mListSheShi = mListSheShi;
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
