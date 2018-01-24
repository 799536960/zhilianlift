package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/24.
 */

public class WoDeBaoBeiModel {

    /**
     * id : 3
     * house_id : 86
     * recommended_mobile : 18772397000
     * recommended_name : 月牙
     * remarks : null
     * recommend_mobile : 18772397060
     * recommend_name : 刘东
     * user_id : 53
     * status : 0
     * premises_name : 这是一个好的楼盘1
     */

    private int id;
    private int house_id;
    private String recommended_mobile;
    private String recommended_name;
    private String remarks;
    private String recommend_mobile;
    private String recommend_name;
    private int user_id;
    private int status;
    private String premises_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getRecommended_mobile() {
        return recommended_mobile;
    }

    public void setRecommended_mobile(String recommended_mobile) {
        this.recommended_mobile = recommended_mobile;
    }

    public String getRecommended_name() {
        return recommended_name;
    }

    public void setRecommended_name(String recommended_name) {
        this.recommended_name = recommended_name;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getRemarksNull() {
        if (remarks == null) {
            return "";
        }
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRecommend_mobile() {
        return recommend_mobile;
    }

    public void setRecommend_mobile(String recommend_mobile) {
        this.recommend_mobile = recommend_mobile;
    }

    public String getRecommend_name() {
        return recommend_name;
    }

    public void setRecommend_name(String recommend_name) {
        this.recommend_name = recommend_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPremises_name() {
        return premises_name;
    }

    public void setPremises_name(String premises_name) {
        this.premises_name = premises_name;
    }
}
