package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/25.
 */

public class GoodsCommentModel {

    /**
     * comment : [{"comment_id":367,"goods_id":121,"order_id":352,"rec_id":450,"store_id":2,"user_id":50,"content":"大萨达撒多撒多撒大所","add_time":1513931505,"ip_address":"","is_show":1,"img":[],"spec_key_name":"颜色:象牙白","goods_rank":"5.0","zan_num":0,"zan_userid":"","reply_num":0,"is_anonymous":1,"impression":"","deleted":0,"parent_id":0,"nickname":"zhangsan","head_pic":"/public/upload/head_pic/20171221/5a2dd8ee1a634696c25942fe7b5e18bb.png","buy_time":1513931306},{"comment_id":293,"goods_id":121,"order_id":1,"rec_id":0,"store_id":2,"user_id":1,"content":"买来送给我老公的, 嘻嘻....","add_time":2016,"ip_address":"127.0.0.1","is_show":1,"img":["/public/upload/goods/2016/04-21/5718379d770a2.jpg","/public/upload/goods/2016/04-21/571837b36fb87.jpg","/public/upload/goods/2016/04-21/571837b34a48e.jpg"],"spec_key_name":"","goods_rank":"3.0","zan_num":0,"zan_userid":"","reply_num":0,"is_anonymous":0,"impression":null,"deleted":0,"parent_id":0,"nickname":"测试人员","head_pic":null,"buy_time":null},{"comment_id":259,"goods_id":121,"order_id":1,"rec_id":0,"store_id":2,"user_id":1,"content":"买回去男朋友很喜欢 ..","add_time":2016,"ip_address":"127.0.0.1","is_show":1,"img":[],"spec_key_name":"","goods_rank":"0.0","zan_num":0,"zan_userid":"","reply_num":0,"is_anonymous":0,"impression":null,"deleted":0,"parent_id":0,"nickname":"测试人员","head_pic":null,"buy_time":null},{"comment_id":225,"goods_id":121,"order_id":1,"rec_id":0,"store_id":2,"user_id":1,"content":"少女们都很喜欢.","add_time":2016,"ip_address":"127.0.0.1","is_show":1,"img":[],"spec_key_name":"","goods_rank":"4.0","zan_num":0,"zan_userid":"","reply_num":0,"is_anonymous":0,"impression":null,"deleted":0,"parent_id":0,"nickname":"测试人员","head_pic":null,"buy_time":null}]
     * commentrate : {"rate1":4,"rate2":2,"rate3":1,"rate4":1}
     */

    private CommentrateBean commentrate;
    private List<CommentModel> comment;

    public CommentrateBean getCommentrate() {
        return commentrate;
    }

    public void setCommentrate(CommentrateBean commentrate) {
        this.commentrate = commentrate;
    }

    public List<CommentModel> getComment() {
        return comment;
    }

    public void setComment(List<CommentModel> comment) {
        this.comment = comment;
    }

    public static class CommentrateBean {
        /**
         * rate1 : 4
         * rate2 : 2
         * rate3 : 1
         * rate4 : 1
         */

        private int rate1;
        private int rate2;
        private int rate3;
        private int rate4;

        public int getRate1() {
            return rate1;
        }

        public void setRate1(int rate1) {
            this.rate1 = rate1;
        }

        public int getRate2() {
            return rate2;
        }

        public void setRate2(int rate2) {
            this.rate2 = rate2;
        }

        public int getRate3() {
            return rate3;
        }

        public void setRate3(int rate3) {
            this.rate3 = rate3;
        }

        public int getRate4() {
            return rate4;
        }

        public void setRate4(int rate4) {
            this.rate4 = rate4;
        }
    }

}
