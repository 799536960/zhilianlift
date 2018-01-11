package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * 商品评论
 * Created by liudong on 2017/12/22.
 */

public class CommentModel {
    private int comment_id;
    private int goods_id;
    private int order_id;
    private int rec_id;
    private int store_id;
    private int user_id;
    private String content;
    private long add_time;
    private String ip_address;
    private int is_show;
    private String spec_key_name;
    private float goods_rank;
    private int zan_num;
    private String zan_userid;
    private int reply_num;
    private int is_anonymous;
    private String impression;
    private int deleted;
    private int parent_id;
    private String head_pic;
    private String nickname;
    private List<String> img;
    private long buy_time;


    public long getAdd_time() {
        return add_time;
    }

    public float getGoods_rank() {
        return goods_rank;
    }

    public void setGoods_rank(float goods_rank) {
        this.goods_rank = goods_rank;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public long getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(long buy_time) {
        this.buy_time = buy_time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }


    public int getZan_num() {
        return zan_num;
    }

    public void setZan_num(int zan_num) {
        this.zan_num = zan_num;
    }

    public String getZan_userid() {
        return zan_userid;
    }

    public void setZan_userid(String zan_userid) {
        this.zan_userid = zan_userid;
    }

    public int getReply_num() {
        return reply_num;
    }

    public void setReply_num(int reply_num) {
        this.reply_num = reply_num;
    }

    public int getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(int is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
