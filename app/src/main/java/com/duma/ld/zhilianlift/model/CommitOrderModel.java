package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2018/1/8.
 */

public class CommitOrderModel implements Serializable {
    /**
     * master_order_sn : 201801081512017529
     * order_amount : 500
     */

    private String master_order_sn;
    private double order_amount;
    private int orderId;

    public CommitOrderModel(double order_amount) {
        this.order_amount = order_amount;
    }

    public CommitOrderModel(String master_order_sn, double order_amount) {
        this.master_order_sn = master_order_sn;
        this.order_amount = order_amount;
    }

    public String getMaster_order_sn() {
        return master_order_sn;
    }

    public void setMaster_order_sn(String master_order_sn) {
        this.master_order_sn = master_order_sn;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(double order_amount) {
        this.order_amount = order_amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
