package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2017/12/5.
 */

public class QuModel {

    /**
     * id : 3
     * name : 东城区
     */

    private int id;
    private String name;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public QuModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
