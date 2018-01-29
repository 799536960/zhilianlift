package com.duma.ld.zhilianlift.model;

import java.io.Serializable;


public class PayStoreModel implements Serializable {
    private String id;
    private String name;
    private String imgUrl;
    private double money;
    private String remark;

    //0装修 1积分 2余额
    private int onClick;
    //积分
    private double pay_points;
    private double renovation_money;
    private double user_money;

    public String getNameType() {
        //0装修 1积分 2余额
        switch (onClick) {
            case 0:
                return "装修资金";
            case 1:
                return "补贴积分";
            case 2:
                return "余额";
        }
        return "";
    }

    public PayStoreModel(String name, String imgUrl, String id) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOnClick() {
        return onClick;
    }

    public void setOnClick(int onClick) {
        this.onClick = onClick;
    }

    public void setOnClickDefaut(int onClick) {
        if (this.onClick == -1) {
            this.onClick = onClick;
        }
    }

    public double getPay_points() {
        return pay_points;
    }

    public void setPay_points(double pay_points) {
        this.pay_points = pay_points;
    }

    public double getRenovation_money() {
        return renovation_money;
    }

    public void setRenovation_money(double renovation_money) {
        this.renovation_money = renovation_money;
    }

    public double getUser_money() {
        return user_money;
    }

    public void setUser_money(double user_money) {
        this.user_money = user_money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
