package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/24.
 */

public class HouseHuXinListModel {

    /**
     * name : 1居
     * key : door_door
     * value : 1
     * list : [{"id":1,"house_id":1,"door_img":"/public/upload/goods/2018/01-19/08ee760e10908b4055631caaa18e786a.jpg","door_price":"23","door_get":"3","door_area":"2","architecture_area":"1","door_door":"1","door_sort":null}]
     * count : 1
     */

    private String name;
    private String key;
    private String value;
    private int count;
    private List<HouseHuXinBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<HouseHuXinBean> getList() {
        return list;
    }

    public void setList(List<HouseHuXinBean> list) {
        this.list = list;
    }
}
