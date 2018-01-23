package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

public class HouseLabelBean implements Serializable {
    /**
     * id : 90
     * house_id : 31
     * hs_id : 53
     * so_name : 满两年
     */

    private int id;
    private int house_id;
    private int hs_id;
    private String so_name;

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

    public int getHs_id() {
        return hs_id;
    }

    public void setHs_id(int hs_id) {
        this.hs_id = hs_id;
    }

    public String getSo_name() {
        return so_name;
    }

    public void setSo_name(String so_name) {
        this.so_name = so_name;
    }
}