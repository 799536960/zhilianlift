package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/25.
 */

public class FinanceListModel {

    /**
     * id : 5
     * plan_text : 这是一个内容
     * plan_name : 方案三
     * plan_img : /public/upload/ad/2018/01-24/07a2d7e925194517df45e661070f38af.jpg
     * plan_type : 1
     * plan_title : 方案三
     * plan_money : 100
     */

    private int id;
    private String plan_text;
    private String plan_name;
    private String plan_img;
    private int plan_type;
    private String plan_title;
    private String plan_money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlan_text() {
        return plan_text;
    }

    public void setPlan_text(String plan_text) {
        this.plan_text = plan_text;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getPlan_img() {
        return plan_img;
    }

    public void setPlan_img(String plan_img) {
        this.plan_img = plan_img;
    }

    public int getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(int plan_type) {
        this.plan_type = plan_type;
    }

    public String getPlan_title() {
        return plan_title;
    }

    public void setPlan_title(String plan_title) {
        this.plan_title = plan_title;
    }

    public String getPlan_money() {
        return plan_money;
    }

    public void setPlan_money(String plan_money) {
        this.plan_money = plan_money;
    }
}
