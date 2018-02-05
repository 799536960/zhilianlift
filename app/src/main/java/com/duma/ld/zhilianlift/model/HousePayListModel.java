package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/16.
 */

public class HousePayListModel {
    /**
     * account_logList : [{"desc":"2017-12-11","order_sn":"0","change_time":1512973029,"renovation_money":"1000.00"},{"desc":"2017-12-11","order_sn":"0","change_time":1512973005,"renovation_money":"1000.00"}]
     * users : {"user_money":"0.00","pay_points":"0.00","renovation_money":"0.00"}
     */

    private UsersBean users;
    private List<AccountLogListBean> account_logList;
    private String pay_points_sum_month;
    private String pay_points_sum;

    public String getPay_points_sum_month() {
        return pay_points_sum_month;
    }

    public String getPay_points_sum_monthNull() {
        if (pay_points_sum_month == null) {
            return "0";
        }
        return pay_points_sum_month;
    }

    public void setPay_points_sum_month(String pay_points_sum_month) {
        this.pay_points_sum_month = pay_points_sum_month;
    }

    public String getPay_points_sum() {
        return pay_points_sum;
    }

    public String getPay_points_sumNulll() {
        if (pay_points_sum == null) {
            return "0";
        }
        return pay_points_sum;
    }

    public void setPay_points_sum(String pay_points_sum) {
        this.pay_points_sum = pay_points_sum;
    }

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public List<AccountLogListBean> getAccount_logList() {
        return account_logList;
    }

    public void setAccount_logList(List<AccountLogListBean> account_logList) {
        this.account_logList = account_logList;
    }

    public static class UsersBean {
        /**
         * user_money : 0.00
         * pay_points : 0.00
         * renovation_money : 0.00
         */

        private String user_money;
        private String pay_points;
        private String renovation_money;

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getRenovation_money() {
            return renovation_money;
        }

        public void setRenovation_money(String renovation_money) {
            this.renovation_money = renovation_money;
        }
    }

    public static class AccountLogListBean {
        /**
         * desc : 2017-12-11
         * order_sn : 0
         * change_time : 1512973029
         * renovation_money : 1000.00
         */

        private String desc;
        private String order_sn;
        private long change_time;
        private double renovation_money;
        private double pay_points;
        private double user_money;

        public double getUser_money() {
            return user_money;
        }

        public void setUser_money(double user_money) {
            this.user_money = user_money;
        }

        public double getPay_points() {
            return pay_points;
        }

        public void setPay_points(double pay_points) {
            this.pay_points = pay_points;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public long getChange_time() {
            return change_time;
        }

        public void setChange_time(long change_time) {
            this.change_time = change_time;
        }

        public double getRenovation_money() {
            return renovation_money;
        }

        public void setRenovation_money(double renovation_money) {
            this.renovation_money = renovation_money;
        }
    }
}
