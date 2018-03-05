package com.duma.ld.zhilianlift.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liudong on 16/12/5.
 */

public class WeiXinModel {

    /**
     * appid : wx43bcc455bb86ae68
     * noncestr : qh2eixo9ahktfsur3y9r1jb0gdtkico5
     * package : Sign=WXPay
     * partnerid : 1499284052
     * prepayid : wx20180305133804933434c8190194587877
     * timestamp : 1520228284
     * paySign : 491489ADB35BAAF8AC7D2137D2750E40
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
