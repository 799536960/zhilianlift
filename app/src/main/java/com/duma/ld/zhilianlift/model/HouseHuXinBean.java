package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2018/1/24.
 */

public class HouseHuXinBean implements Serializable {
    private int id;
    private int house_id;
    private String door_img;
    private String door_price;
    private String door_get;
    private String door_area;
    private String architecture_area;
    private String door_door;
    private String door_sort;
    private String scale;
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

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

    public String getDoor_img() {
        return door_img;
    }

    public void setDoor_img(String door_img) {
        this.door_img = door_img;
    }

    public String getDoor_price() {
        return door_price;
    }

    public void setDoor_price(String door_price) {
        this.door_price = door_price;
    }

    public String getDoor_get() {
        return door_get;
    }

    public void setDoor_get(String door_get) {
        this.door_get = door_get;
    }

    public String getDoor_area() {
        return door_area;
    }

    public void setDoor_area(String door_area) {
        this.door_area = door_area;
    }

    public String getArchitecture_area() {
        return architecture_area;
    }

    public void setArchitecture_area(String architecture_area) {
        this.architecture_area = architecture_area;
    }

    public String getDoor_door() {
        return door_door;
    }

    public void setDoor_door(String door_door) {
        this.door_door = door_door;
    }

    public String getDoor_sort() {
        return door_sort;
    }

    public void setDoor_sort(String door_sort) {
        this.door_sort = door_sort;
    }
}
