package com.duma.ld.zhilianlift.model;

import com.duma.ld.baselibrary.util.ZhuanHuanUtil;

/**
 * Created by liudong on 2017/12/7.
 */

public class UserModel {

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", user_money='" + user_money + '\'' +
                ", frozen_money='" + frozen_money + '\'' +
                ", distribut_money='" + distribut_money + '\'' +
                ", pay_points=" + pay_points +
                ", paypwd='" + paypwd + '\'' +
                ", reg_time=" + reg_time +
                ", last_login=" + last_login +
                ", last_ip='" + last_ip + '\'' +
                ", qq='" + qq + '\'' +
                ", mobile='" + mobile + '\'' +
                ", mobile_validated=" + mobile_validated +
                ", oauth='" + oauth + '\'' +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", head_pic='" + head_pic + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", bank_card='" + bank_card + '\'' +
                ", realname='" + realname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", email_validated=" + email_validated +
                ", nickname='" + nickname + '\'' +
                ", level=" + level +
                ", discount='" + discount + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", is_lock=" + is_lock +
                ", is_distribut=" + is_distribut +
                ", first_leader=" + first_leader +
                ", second_leader=" + second_leader +
                ", third_leader=" + third_leader +
                ", token='" + token + '\'' +
                ", underling_number=" + underling_number +
                ", message_mask=" + message_mask +
                ", push_id='" + push_id + '\'' +
                ", renovation_money='" + renovation_money + '\'' +
                ", certification_is='" + certification_is + '\'' +
                ", coupon_count='" + coupon_count + '\'' +
                ", collect_count='" + collect_count + '\'' +
                ", focus_count='" + focus_count + '\'' +
                ", visit_count='" + visit_count + '\'' +
                ", return_count='" + return_count + '\'' +
                ", waitPay='" + waitPay + '\'' +
                ", waitSend='" + waitSend + '\'' +
                ", waitcomment='" + waitcomment + '\'' +
                ", waitReceive='" + waitReceive + '\'' +
                ", message_count='" + message_count + '\'' +
                ", comment_count='" + comment_count + '\'' +
                ", uncomment_count='" + uncomment_count + '\'' +
                ", serve_comment_count='" + serve_comment_count + '\'' +
                ", cart_goods_num='" + cart_goods_num + '\'' +
                ", news='" + news + '\'' +
                '}';
    }

    /**
     * user_id : 51
     * email :
     * password : 519475228fe35ad067744465c42a19b2
     * sex : 0
     * birthday : 0
     * user_money : 0.00
     * frozen_money : 0.00
     * distribut_money : 0.00
     * pay_points : 0
     * paypwd : null
     * reg_time : 1512641703
     * last_login : 1512970988
     * last_ip :
     * qq :
     * mobile : 18772397060
     * mobile_validated : 1
     * oauth :
     * openid : null
     * unionid : null
     * head_pic : null
     * bank_name : null
     * bank_card : null
     * realname : null
     * idcard : null
     * email_validated : 0
     * nickname : null
     * level : 1
     * discount : 1.00
     * total_amount : 0.00
     * is_lock : 0
     * is_distribut : 1
     * first_leader : 0
     * second_leader : 0
     * third_leader : 0
     * token : dc2480bca1005b98c16d2ab72a43ea95
     * underling_number : 0
     * message_mask : 63
     * push_id :
     * renovation_money : 0.00
     * certification_is : 0
     * coupon_count : 0
     * collect_count : 0
     * focus_count : 0
     * visit_count : 0
     * return_count : 0
     * waitPay : 0
     * waitSend : 0
     * waitcomment : 0
     * waitReceive : 0
     * message_count : 0
     * comment_count : 0
     * uncomment_count : 0
     * serve_comment_count : 0
     * cart_goods_num : 0
     * news : 10
     */


    private long user_id;
    private String email;
    private String password;
    private int sex;
    private long birthday;
    private String user_money;
    private String frozen_money;
    private String distribut_money;
    private long pay_points;
    private String paypwd;
    private long reg_time;
    private long last_login;
    private String last_ip;
    private String qq;
    private String mobile;
    private int mobile_validated;
    private String oauth;
    private String openid;
    private String unionid;
    private String head_pic;
    private String bank_name;
    private String bank_card;
    private String realname;
    private String idcard;
    private int email_validated;
    private String nickname;
    private int level;
    private String discount;
    private String total_amount;
    private int is_lock;
    private int is_distribut;
    private int first_leader;
    private int second_leader;
    private int third_leader;
    private String token;
    private int underling_number;
    private int message_mask;
    private String push_id;
    //装修资金
    private String renovation_money;
    private String certification_is;
    private String coupon_count;
    private String collect_count;
    private String focus_count;
    private String visit_count;
    private String return_count;
    private String waitPay;
    private String waitSend;
    private String waitcomment;
    private String waitReceive;
    private String message_count;
    private String comment_count;
    private String uncomment_count;
    private String serve_comment_count;
    private String cart_goods_num;
    private String news;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        switch (sex) {
            case 3:
                return "保密";
            case 1:
                return "男";
            default:
                return "女";
        }
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getUser_money() {
        if (user_money == null || user_money.isEmpty()) {
            return "0";
        }
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getFrozen_money() {
        return frozen_money;
    }

    public void setFrozen_money(String frozen_money) {
        this.frozen_money = frozen_money;
    }

    public String getDistribut_money() {
        return distribut_money;
    }

    public void setDistribut_money(String distribut_money) {
        this.distribut_money = distribut_money;
    }

    public long getPay_points() {
        return pay_points;
    }

    public void setPay_points(long pay_points) {
        this.pay_points = pay_points;
    }

    public String getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
    }

    public long getReg_time() {
        return reg_time;
    }

    public void setReg_time(long reg_time) {
        this.reg_time = reg_time;
    }

    public long getLast_login() {
        return last_login;
    }

    public void setLast_login(long last_login) {
        this.last_login = last_login;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMobile_xx() {
        if (mobile != null && !mobile.isEmpty()) {
            return ZhuanHuanUtil.setPhoneXX(mobile);
        }
        return mobile + "";
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobile_validated() {
        return mobile_validated;
    }

    public void setMobile_validated(int mobile_validated) {
        this.mobile_validated = mobile_validated;
    }

    public String getOauth() {
        return oauth;
    }

    public void setOauth(String oauth) {
        this.oauth = oauth;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getEmail_validated() {
        return email_validated;
    }

    public void setEmail_validated(int email_validated) {
        this.email_validated = email_validated;
    }

    public String getNickname() {
        if (nickname == null || nickname.isEmpty()) {
            return "<未设置>";
        }
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public int getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(int is_lock) {
        this.is_lock = is_lock;
    }

    public int getIs_distribut() {
        return is_distribut;
    }

    public void setIs_distribut(int is_distribut) {
        this.is_distribut = is_distribut;
    }

    public int getFirst_leader() {
        return first_leader;
    }

    public void setFirst_leader(int first_leader) {
        this.first_leader = first_leader;
    }

    public int getSecond_leader() {
        return second_leader;
    }

    public void setSecond_leader(int second_leader) {
        this.second_leader = second_leader;
    }

    public int getThird_leader() {
        return third_leader;
    }

    public void setThird_leader(int third_leader) {
        this.third_leader = third_leader;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUnderling_number() {
        return underling_number;
    }

    public void setUnderling_number(int underling_number) {
        this.underling_number = underling_number;
    }

    public int getMessage_mask() {
        return message_mask;
    }

    public void setMessage_mask(int message_mask) {
        this.message_mask = message_mask;
    }

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    public String getRenovation_money() {
        if (renovation_money == null || renovation_money.isEmpty()) {
            return "0";
        }
        return renovation_money;
    }

    public void setRenovation_money(String renovation_money) {
        this.renovation_money = renovation_money;
    }

    public String getCertification_is() {
        return certification_is;
    }

    public void setCertification_is(String certification_is) {
        this.certification_is = certification_is;
    }

    public String getCoupon_count() {
        if (coupon_count == null || coupon_count.isEmpty()) {
            return "0";
        }
        return coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(String collect_count) {
        this.collect_count = collect_count;
    }

    public String getFocus_count() {
        return focus_count;
    }

    public void setFocus_count(String focus_count) {
        this.focus_count = focus_count;
    }

    public String getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(String visit_count) {
        this.visit_count = visit_count;
    }

    public String getReturn_count() {
        if (return_count == null || return_count.isEmpty()) {
            return "0";
        }
        return return_count;
    }

    public void setReturn_count(String return_count) {
        this.return_count = return_count;
    }

    public String getWaitPay() {
        if (waitPay == null || waitPay.isEmpty()) {
            return "0";
        }
        return waitPay;
    }

    public void setWaitPay(String waitPay) {
        this.waitPay = waitPay;
    }

    public String getWaitSend() {
        if (waitSend == null || waitSend.isEmpty()) {
            return "0";
        }
        return waitSend;
    }

    public void setWaitSend(String waitSend) {
        this.waitSend = waitSend;
    }

    public String getWaitcomment() {
        return waitcomment;
    }

    public void setWaitcomment(String waitcomment) {
        this.waitcomment = waitcomment;
    }

    public String getWaitReceive() {
        if (waitReceive == null || waitReceive.isEmpty()) {
            return "0";
        }
        return waitReceive;
    }

    public void setWaitReceive(String waitReceive) {
        this.waitReceive = waitReceive;
    }

    public String getMessage_count() {
        return message_count;
    }

    public void setMessage_count(String message_count) {
        this.message_count = message_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getUncomment_count() {
        return uncomment_count;
    }

    public void setUncomment_count(String uncomment_count) {
        this.uncomment_count = uncomment_count;
    }

    public String getServe_comment_count() {
        return serve_comment_count;
    }

    public void setServe_comment_count(String serve_comment_count) {
        this.serve_comment_count = serve_comment_count;
    }

    public String getCart_goods_num() {
        return cart_goods_num;
    }

    public void setCart_goods_num(String cart_goods_num) {
        this.cart_goods_num = cart_goods_num;
    }

    public String getNews() {
        if (news == null || news.isEmpty()) {
            return "0";
        }
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}

