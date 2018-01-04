package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/4.
 */

public class CouponsModel {

    /**
     * id : 104
     * uid : 51
     * cid : 27
     * use_start_time : 1512116993
     * use_end_time : 1519720193
     * name : 真的无语
     * money : 200.00
     * condition : 400.00
     */

    private int id;
    private int uid;
    private int cid;
    private int use_start_time;
    private int use_end_time;
    private String name;
    private String money;
    private String condition;
    //0 未使用 1已使用 2已过期
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUse_start_time() {
        return use_start_time;
    }

    public void setUse_start_time(int use_start_time) {
        this.use_start_time = use_start_time;
    }

    public int getUse_end_time() {
        return use_end_time;
    }

    public void setUse_end_time(int use_end_time) {
        this.use_end_time = use_end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
