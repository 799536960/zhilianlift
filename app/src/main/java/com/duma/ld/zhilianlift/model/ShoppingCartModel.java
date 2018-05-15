package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/28.
 */

public class ShoppingCartModel {

    private TotalPriceBean total_price;
    private List<ShoppingCartStoreModel> storeList;

    public TotalPriceBean getTotal_price() {
        return total_price;
    }

    public void setTotal_price(TotalPriceBean total_price) {
        this.total_price = total_price;
    }

    public List<ShoppingCartStoreModel> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<ShoppingCartStoreModel> storeList) {
        this.storeList = storeList;
    }

    public static class TotalPriceBean {
        private String total_fee;
        private int cut_fee;
        private int num;

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public int getCut_fee() {
            return cut_fee;
        }

        public void setCut_fee(int cut_fee) {
            this.cut_fee = cut_fee;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

}
