package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/15.
 */

public class MessageModel {
    /**
     * id : 10
     * type : 1
     * context : 您的账户余额本次消费2714.8元,请确认是否是本人操作，如有问题请联系智联生活客服
     * type_name : 余额变动通知
     * status : 1
     * user_id : 50
     * add_time : 1516010016
     */

    private int id;
    private int type;
    private String context;
    private String type_name;
    private int status;
    private int user_id;
    private long add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }
}
