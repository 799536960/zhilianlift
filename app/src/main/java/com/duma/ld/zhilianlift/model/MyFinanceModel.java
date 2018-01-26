package com.duma.ld.zhilianlift.model;

/**
 * Created by liudong on 2018/1/26.
 */

public class MyFinanceModel {


    /**
     * id : 16
     * realname : 还没
     * idcard : 420521199306270010
     * address : 还没
     * idcard_img1 : /public/upload/idcard/20180125/90bd3a6fa3b297edb5e410ac30d6e038.jpg
     * idcard_img2 : /public/upload/idcard/20180125/db2b8fa3c03b77a15c6150191d497581.jpg
     * idcard_img3 : /public/upload/idcard/20180125/86f398d9a925973706da1d01bd66c3ad.jpg
     * house_img1 : /public/upload/house_img/20180125/1e85132a617b9b8519a97d4ae6d164eb.jpg
     * house_img2 : /public/upload/house_img/20180125/3639dc74c87ac444103b93e26f79ae3a.png
     * credit_is : 3
     * is_why : keyi
     * user_id : 53
     * plan_id : 4
     * money : 64646
     * plan_type : 0
     */

    private int id;
    private String realname;
    private String idcard;
    private String address;
    private String idcard_img1;
    private String idcard_img2;
    private String idcard_img3;
    private String house_img1;
    private String house_img2;
    //0 真在 1通过 2失败 3放款
    private int credit_is;
    private String is_why;
    private int user_id;
    private int plan_id;
    private String money;
    private int plan_type;
    private String plan_name;
    private long add_time;

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard_img1() {
        return idcard_img1;
    }

    public void setIdcard_img1(String idcard_img1) {
        this.idcard_img1 = idcard_img1;
    }

    public String getIdcard_img2() {
        return idcard_img2;
    }

    public void setIdcard_img2(String idcard_img2) {
        this.idcard_img2 = idcard_img2;
    }

    public String getIdcard_img3() {
        return idcard_img3;
    }

    public void setIdcard_img3(String idcard_img3) {
        this.idcard_img3 = idcard_img3;
    }

    public String getHouse_img1() {
        return house_img1;
    }

    public void setHouse_img1(String house_img1) {
        this.house_img1 = house_img1;
    }

    public String getHouse_img2() {
        return house_img2;
    }

    public void setHouse_img2(String house_img2) {
        this.house_img2 = house_img2;
    }

    public int getCredit_is() {
        return credit_is;
    }

    public void setCredit_is(int credit_is) {
        this.credit_is = credit_is;
    }

    public String getIs_why() {
        return is_why;
    }

    public void setIs_why(String is_why) {
        this.is_why = is_why;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(int plan_type) {
        this.plan_type = plan_type;
    }
}
