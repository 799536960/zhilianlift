package com.duma.ld.zhilianlift.model;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 2018/1/25.
 */

public class AddFinanceModel implements Serializable {
    //    I('post.realname') ? $post['realname'] = I('post.realname') : false; //真实姓名
//    I('post.idcard') ? $post['idcard'] = I('post.idcard') : false; //身份证号码
//    I('post.address') ? $post['address'] = I('post.address') : false; //房屋地址
//    I('post.money') ? $post['money'] = I('post.money') : false; //房屋地址
//    I('post.plan_id') ? $post['plan_id'] = I('post.plan_id') : false; //方案
//    idcard_img[]   身份证图片
//    house_img[]    房产图片
    private String realname;
    private String idcard;
    private String address;
    private String money;
    private String plan_id;
    private List<File> idcard_img;
    private List<File> house_img;

    @Override
    public String toString() {
        return "AddFinanceModel{" +
                "realname='" + realname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", address='" + address + '\'' +
                ", money='" + money + '\'' +
                ", plan_id='" + plan_id + '\'' +
                ", idcard_img=" + idcard_img +
                ", house_img=" + house_img +
                '}';
    }

    public AddFinanceModel(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public List<File> getIdcard_img() {
        return idcard_img;
    }

    public void setIdcard_img(List<File> idcard_img) {
        this.idcard_img = idcard_img;
    }

    public List<File> getHouse_img() {
        return house_img;
    }

    public void setHouse_img(List<File> house_img) {
        this.house_img = house_img;
    }
}
