package com.duma.ld.zhilianlift.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/12/14.
 */

public class RealNameModel implements Serializable{

    /**
     * certification_is : 3
     */

    private int certification_is;
    /**
     * id : 14
     * user_id : 51
     * idcard : 420521199306270010
     * realname : yyyy
     * idcard_img1 : /public/upload/idcard/20171215/304c8b6bbb7794c479e9657cb5c46580.JPEG
     * idcard_img2 : /public/upload/idcard/20171215/304c8b6bbb7794c479e9657cb5c46580.jpg
     * idcard_img3 : /public/upload/idcard/20171215/8d4129bf9fee5492487db3b5b048823b.jpg
     * is_why : ggg
     */

    private int id;
    private int user_id;
    private String idcard;
    private String realname;
    private String idcard_img1;
    private String idcard_img2;
    private String idcard_img3;
    private String is_why;

    public int getCertification_is() {
        return certification_is;
    }

    public void setCertification_is(int certification_is) {
        this.certification_is = certification_is;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public String getIs_why() {
        return is_why;
    }

    public void setIs_why(String is_why) {
        this.is_why = is_why;
    }
}
