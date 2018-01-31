package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2018/1/31.
 */

public class ZhiFuBaoUserModel implements Serializable{

    /**
     * user_money : 994593.00
     * alipay_name : null
     * alipay_realname : null
     */

    private double user_money;
    private String alipay_name;
    private String alipay_realname;

    public double getUser_money() {
        return user_money;
    }

    public void setUser_money(double user_money) {
        this.user_money = user_money;
    }

    public String getAlipay_name() {
        return alipay_name;
    }

    public void setAlipay_name(String alipay_name) {
        this.alipay_name = alipay_name;
    }

    public String getAlipay_realname() {
        return alipay_realname;
    }

    public void setAlipay_realname(String alipay_realname) {
        this.alipay_realname = alipay_realname;
    }
}
