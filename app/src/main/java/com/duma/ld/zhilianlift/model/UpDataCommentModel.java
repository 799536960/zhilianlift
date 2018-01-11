package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/11.
 */

public class UpDataCommentModel {

    /**
     * goods_id : 61
     * rank : 5
     * spec_key_name : 尺寸:55
     * content : zzzzzzooooo
     * rec_id : 560
     * img1 : /public/upload/idcard/20180111/db4a1bd0253b10fd5d74e156f38e1ab2.png
     * img2 : /public/upload/idcard/20180111/db4a1bd0253b10fd5d74e156f38e1ab2.png
     */

    private int goods_id;
    private int rank;
    private String spec_key_name;
    private String content;
    private int rec_id;
    private int hide_username;
    private List<String> imgList;


    public int getHide_username() {
        return hide_username;
    }

    public void setHide_username(int hide_username) {
        this.hide_username = hide_username;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }
}
