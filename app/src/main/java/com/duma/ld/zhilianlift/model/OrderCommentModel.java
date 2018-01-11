package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/11.
 */

public class OrderCommentModel {
    /**
     * order_info : {"order_id":450,"order_sn":"201801111105132379","is_comment":0,"add_time":1515639913,"order_prom_type":0,"no_comment_goods_list":[{"rec_id":560,"order_id":450,"goods_id":61,"goods_name":"海信彩电LED55EC520UA 55英寸 14核 4K智能电视(黑色)","spec_key_name":"尺寸:55","goods_price":"4399.00"},{"rec_id":561,"order_id":450,"goods_id":129,"goods_name":"【蚂蚁摄影】Canon/佳能 PowerShot SX410 IS 媲单反长焦数码相机","spec_key_name":"","goods_price":"1098.00"}],"no_comment_goods_list_count":2,"store":{"store_id":2,"store_name":"海澜之家","store_phone":"0755-86140794","store_address":"西丽镇留仙大道1001号","store_logo":"/public/upload/seller/2016/10-28/5812c2b8db09b.jpg"}}
     */

    private OrderInfoBean order_info;

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public static class OrderInfoBean {
        /**
         * order_id : 450
         * order_sn : 201801111105132379
         * is_comment : 0
         * add_time : 1515639913
         * order_prom_type : 0
         * no_comment_goods_list : [{"rec_id":560,"order_id":450,"goods_id":61,"goods_name":"海信彩电LED55EC520UA 55英寸 14核 4K智能电视(黑色)","spec_key_name":"尺寸:55","goods_price":"4399.00"},{"rec_id":561,"order_id":450,"goods_id":129,"goods_name":"【蚂蚁摄影】Canon/佳能 PowerShot SX410 IS 媲单反长焦数码相机","spec_key_name":"","goods_price":"1098.00"}]
         * no_comment_goods_list_count : 2
         * store : {"store_id":2,"store_name":"海澜之家","store_phone":"0755-86140794","store_address":"西丽镇留仙大道1001号","store_logo":"/public/upload/seller/2016/10-28/5812c2b8db09b.jpg"}
         */

        private int order_id;
        private String order_sn;
        private int is_comment;
        private int add_time;
        private int order_prom_type;
        private int no_comment_goods_list_count;
        private StoreBean store;
        private List<NoCommentGoodsListBean> no_comment_goods_list;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getOrder_prom_type() {
            return order_prom_type;
        }

        public void setOrder_prom_type(int order_prom_type) {
            this.order_prom_type = order_prom_type;
        }

        public int getNo_comment_goods_list_count() {
            return no_comment_goods_list_count;
        }

        public void setNo_comment_goods_list_count(int no_comment_goods_list_count) {
            this.no_comment_goods_list_count = no_comment_goods_list_count;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public List<NoCommentGoodsListBean> getNo_comment_goods_list() {
            return no_comment_goods_list;
        }

        public void setNo_comment_goods_list(List<NoCommentGoodsListBean> no_comment_goods_list) {
            this.no_comment_goods_list = no_comment_goods_list;
        }

        public static class StoreBean {
            /**
             * store_id : 2
             * store_name : 海澜之家
             * store_phone : 0755-86140794
             * store_address : 西丽镇留仙大道1001号
             * store_logo : /public/upload/seller/2016/10-28/5812c2b8db09b.jpg
             */

            private int store_id;
            private String store_name;
            private String store_phone;
            private String store_address;
            private String store_logo;

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStore_phone() {
                return store_phone;
            }

            public void setStore_phone(String store_phone) {
                this.store_phone = store_phone;
            }

            public String getStore_address() {
                return store_address;
            }

            public void setStore_address(String store_address) {
                this.store_address = store_address;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }
        }

        public static class NoCommentGoodsListBean {
            /**
             * rec_id : 560
             * order_id : 450
             * goods_id : 61
             * goods_name : 海信彩电LED55EC520UA 55英寸 14核 4K智能电视(黑色)
             * spec_key_name : 尺寸:55
             * goods_price : 4399.00
             */

            private int rec_id;
            private int order_id;
            private int goods_id;
            private String goods_name;
            private String spec_key_name;
            private String goods_price;
            private String comment;
            private int comment_star;
            private List<String> mImageList;
            //是否匿名 默认是 true
            private boolean isUser;
            private String original_img;

            //默认
            public NoCommentGoodsListBean() {
                isUser = true;
                comment_star = 5;
            }

            public int getComment_star() {
                return comment_star;
            }

            public void setComment_star(int comment_star) {
                this.comment_star = comment_star;
            }

            public List<String> getmImageList() {
                return mImageList;
            }

            public void setmImageList(List<String> mImageList) {
                this.mImageList = mImageList;
            }

            public boolean isUser() {
                return isUser;
            }

            //匿名 1
            public int getUser() {
                if (isUser) {
                    return 1;
                }
                return 0;
            }

            public void setUser(boolean user) {
                isUser = user;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }


            public int getRec_id() {
                return rec_id;
            }

            public void setRec_id(int rec_id) {
                this.rec_id = rec_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }
        }
    }
}
