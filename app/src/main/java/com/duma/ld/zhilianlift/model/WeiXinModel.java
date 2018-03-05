package com.duma.ld.zhilianlift.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liudong on 16/12/5.
 */

public class WeiXinModel {

    /**
     * appid : wx43bcc455bb86ae68
     * noncestr : uvl0brnz2f6wthvlmh1g5gkzocvv32cs
     * package : Sign=WXPay
     * partnerid : 1499284052
     * prepayid : wx20180305111133a81f6359a00963757434
     * timeStamp : 1520219493
     * paySign : 7EC0BDC5435C5FE5D8A52B05BB3B1BA3
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String timeStamp;
    private String paySign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
