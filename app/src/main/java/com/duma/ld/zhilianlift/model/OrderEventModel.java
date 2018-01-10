package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/10.
 */

public class OrderEventModel {
    private int position;
    //是否删除 不是删除 就是更新
    private boolean isDelete;
    //更新的话不能为空
    private OrderModel model;
    //判断是哪一个fragment发过来的
    private String type;
    //是否发送
    private boolean isSend;


    public OrderEventModel(int position, String type) {
        this.position = position;
        this.type = type;
        isSend = false;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public String getType() {
        if (type == null) {
            return "";
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDelete() {
        return isDelete;
    }

    //删除
    public void setDelete(boolean delete) {
        isSend = true;
        isDelete = delete;
    }

    public OrderModel getModel() {
        return model;
    }

    //更新
    public void setModel(OrderModel model) {
        this.model = model;
        isDelete = false;
        isSend = true;
    }
}
