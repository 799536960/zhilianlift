package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/26.
 */

public class FinanceInfoModel {
    /**
     * schedule_creditList : [{"id":3,"context":"管理员已放款,如有问题请联系智联生活客服","credit_id":16,"status":3,"add_time":"1516931708"},{"id":2,"context":"您的申请已经审核通过,待管理员放款","credit_id":16,"status":1,"add_time":"1516931238"},{"id":1,"context":"您的申请已经提交，待管理员审核","credit_id":16,"status":0,"add_time":"1516879220"}]
     * credit : {"id":16,"realname":"还没","idcard":"420521199306270010","address":"还没","idcard_img1":"/public/upload/idcard/20180125/90bd3a6fa3b297edb5e410ac30d6e038.jpg","idcard_img2":"/public/upload/idcard/20180125/db2b8fa3c03b77a15c6150191d497581.jpg","idcard_img3":"/public/upload/idcard/20180125/86f398d9a925973706da1d01bd66c3ad.jpg","house_img1":"/public/upload/house_img/20180125/1e85132a617b9b8519a97d4ae6d164eb.jpg","house_img2":"/public/upload/house_img/20180125/3639dc74c87ac444103b93e26f79ae3a.png","credit_is":3,"is_why":"keyi","user_id":53,"plan_id":4,"money":"64646","plan_type":0,"add_time":"1516931238","plan_name":"方案四"}
     */

    private MyFinanceModel credit;
    private List<ScheduleCreditListBean> schedule_creditList;

    public MyFinanceModel getCredit() {
        return credit;
    }

    public void setCredit(MyFinanceModel credit) {
        this.credit = credit;
    }

    public List<ScheduleCreditListBean> getSchedule_creditList() {
        return schedule_creditList;
    }

    public void setSchedule_creditList(List<ScheduleCreditListBean> schedule_creditList) {
        this.schedule_creditList = schedule_creditList;
    }


    public static class ScheduleCreditListBean {
        /**
         * id : 3
         * context : 管理员已放款,如有问题请联系智联生活客服
         * credit_id : 16
         * status : 3
         * add_time : 1516931708
         */

        private int id;
        private String context;
        private int credit_id;
        private int status;
        private long add_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public int getCredit_id() {
            return credit_id;
        }

        public void setCredit_id(int credit_id) {
            this.credit_id = credit_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }
    }
}
