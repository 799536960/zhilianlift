package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/29.
 */

public class GetPayMoneyModel {

    /**
     * pay_points : 1
     * renovation_money : 1
     * users : {"pay_points":"875302.20","renovation_money":"1048960.10","user_money":"994593.00"}
     */

    private int pay_points;
    private int renovation_money;
    private UsersBean users;

    public int getPay_points() {
        return pay_points;
    }

    public void setPay_points(int pay_points) {
        this.pay_points = pay_points;
    }

    public int getRenovation_money() {
        return renovation_money;
    }

    public void setRenovation_money(int renovation_money) {
        this.renovation_money = renovation_money;
    }

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * pay_points : 875302.20
         * renovation_money : 1048960.10
         * user_money : 994593.00
         */

        private double pay_points;
        private double renovation_money;
        private double user_money;

        public double getPay_points() {
            return pay_points;
        }

        public void setPay_points(double pay_points) {
            this.pay_points = pay_points;
        }

        public double getRenovation_money() {
            return renovation_money;
        }

        public void setRenovation_money(double renovation_money) {
            this.renovation_money = renovation_money;
        }

        public double getUser_money() {
            return user_money;
        }

        public void setUser_money(double user_money) {
            this.user_money = user_money;
        }
    }
}
