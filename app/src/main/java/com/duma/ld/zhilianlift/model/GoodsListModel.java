package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/20.
 */

public class GoodsListModel {

    /**
     * goods_list : [{"goods_id":65,"original_img":"/public/upload/goods/2016/01-15/569856c42e7b7.jpg","cat_id3":100,"goods_sn":"TP0000065","goods_name":"长虹(CHANGHONG) 49A1U 49英寸双64位4K超清智能网络LED液晶电视","shop_price":"2799.00","comment_count":3,"sales_sum":0,"is_virtual":0,"good_comment_rate":100},{"goods_id":97,"original_img":"/public/upload/goods/2016/01-21/56a08e3362c6f.jpg","cat_id3":100,"goods_sn":"TP0000097","goods_name":"六福珠宝足金心形DIY刻字片吊坠黄金项链手链挂饰计价GMGTBP0056","shop_price":"279.00","comment_count":3,"sales_sum":0,"is_virtual":0,"good_comment_rate":34},{"goods_id":104,"original_img":"/public/upload/goods/2016/03-12/56e3eb73912ff.jpg","cat_id3":100,"goods_sn":"TP0000104","goods_name":"小米手机5,十余项黑科技，很轻狠快","shop_price":"1999.00","comment_count":5,"sales_sum":0,"is_virtual":0,"good_comment_rate":80},{"goods_id":115,"original_img":"/public/upload/goods/2016/04-20/57175059527f4.jpg","cat_id3":100,"goods_sn":"TP0000115","goods_name":"爸爸2陆毅代言索扬20000毫安充电宝轻薄正品手机通用移动电源MAh","shop_price":"0.01","comment_count":1,"sales_sum":1,"is_virtual":0,"good_comment_rate":25},{"goods_id":116,"original_img":"/public/upload/goods/2016/04-20/57175203bd715.jpg","cat_id3":100,"goods_sn":"TP0000116","goods_name":"ROMOSS/罗马仕 sense6 20000M毫安充电宝 正品手机通用移动电源","shop_price":"89.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":34},{"goods_id":117,"original_img":"/public/upload/goods/2016/04-21/57182dd9c365c.jpg","cat_id3":100,"goods_sn":"TP0000117","goods_name":"小派M-20000超薄充电宝适用MIUI蘋果6s手机通用毫安移动电源便携","shop_price":"69.90","comment_count":1,"sales_sum":1,"is_virtual":0,"good_comment_rate":75},{"goods_id":118,"original_img":"/public/upload/goods/2016/04-21/571835c77b583.jpg","cat_id3":100,"goods_sn":"TP0000118","goods_name":"ROMOSS/罗马仕 sense4 正品10000+毫安移动电源 手机通用充电宝","shop_price":"59.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":0},{"goods_id":119,"original_img":"/public/upload/goods/2016/04-21/5718365665d97.jpg","cat_id3":100,"goods_sn":"TP0000119","goods_name":"小米旗舰店正品手机平板通用迷你充电宝 移动电源10000毫安大容量","shop_price":"69.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":0},{"goods_id":120,"original_img":"/public/upload/goods/2016/04-21/571836e30aaba.jpg","cat_id3":100,"goods_sn":"TP0000120","goods_name":"小米旗舰店正品手机平板通用移动电源16000毫安大容量品牌充电宝","shop_price":"129.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":34},{"goods_id":121,"original_img":"/public/upload/goods/2016/04-21/571837b30942a.jpg","cat_id3":100,"goods_sn":"TP0000121","goods_name":"科智50000通用充电宝20000毫安冲手机蘋果6s超薄可爱便携移动电源","shop_price":"69.90","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":34},{"goods_id":122,"original_img":"/public/upload/goods/2016/04-21/5718384936f8a.jpg","cat_id3":100,"goods_sn":"TP0000122","goods_name":"黛尔尼曼20000M蘋果6s手机通用超薄移动电源冲充电宝MIUI便携毫安","shop_price":"69.90","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":34},{"goods_id":135,"original_img":"/public/upload/goods/2016/04-21/5718883514695.jpg","cat_id3":100,"goods_sn":"TP0000135","goods_name":"重庆电信手机卡电话卡语音卡选靓号3G4G卡内部5折卡低资费（飞）","shop_price":"60.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":0},{"goods_id":136,"original_img":"/public/upload/goods/2016/04-21/57188888e18e2.jpg","cat_id3":100,"goods_sn":"TP0000136","goods_name":"靓号0元送广东联通4G/3G手机卡上网卡大流量套餐全国无漫游选号","shop_price":"120.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":67},{"goods_id":143,"original_img":"/public/upload/goods/2016/04-22/5719923fb2708.jpg","cat_id3":100,"goods_sn":"TP0000143","goods_name":"haier海尔 BC-93TMPF 93升单门冰箱","shop_price":"699.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":34},{"goods_id":159,"original_img":"/public/upload/goods/2016/11-02/58194eedb226c.jpg","cat_id3":100,"goods_sn":"P0000122323","goods_name":"魅族 MX5 16GB 灰色 移动联通双4G手机 双卡双待","shop_price":"1999.00","comment_count":0,"sales_sum":0,"is_virtual":0,"good_comment_rate":100}]
     * filter_attr : [{"name":"内存容量","item":[{"name":"64G","href":"/index.php/api/Goods/goodsList/id/12/attr/68_64G","key":"attr","value":"68_64G","id":1},{"name":"8G","href":"/index.php/api/Goods/goodsList/id/12/attr/68_8G","key":"attr","value":"68_8G","id":2}]},{"name":"产品名称","item":[{"name":"科智 50000","href":"/index.php/api/Goods/goodsList/id/12/attr/321_科智 50000","key":"attr","value":"321_科智 50000","id":3}]},{"name":"产品","item":[{"name":"科智","href":"/index.php/api/Goods/goodsList/id/12/attr/322_科智","key":"attr","value":"322_科智","id":4}]},{"name":"电池容量","item":[{"name":"20000mAh","href":"/index.php/api/Goods/goodsList/id/12/attr/323_20000mAh","key":"attr","value":"323_20000mAh","id":5}]},{"name":"操作系统","item":[{"name":"android4.0","href":"/index.php/api/Goods/goodsList/id/12/attr/69_android4.0","key":"attr","value":"69_android4.0","id":6}]},{"name":"品牌","item":[{"name":"现代/HYUNDAI","key":"brand_id","value":17,"href":"/index.php/api/Goods/goodsList/id/12/brand_id/17","id":7},{"name":"小米","key":"brand_id","value":349,"href":"/index.php/api/Goods/goodsList/id/12/brand_id/349","id":8}]},{"name":"价格","item":[{"name":"0-560","key":"price","value":"0-560","href":"/index.php/api/Goods/goodsList/id/12/price/0-560","id":9},{"name":"560-1120","key":"price","value":"560-1120","href":"/index.php/api/Goods/goodsList/id/12/price/560-1120","id":10},{"name":"1680-2240","key":"price","value":"1680-2240","href":"/index.php/api/Goods/goodsList/id/12/price/1680-2240","id":11},{"name":"2240-2800","key":"price","value":"2240-2800","href":"/index.php/api/Goods/goodsList/id/12/price/2240-2800","id":12}]}]
     * sort : goods_id
     * sort_asc : asc
     * orderby_default : /index.php/api/Goods/goodsList/id/12
     * orderby_sales_sum : /index.php/api/Goods/goodsList/id/12/sort/sales_sum/sort_asc/desc
     * orderby_price : /index.php/api/Goods/goodsList/id/12/sort/shop_price/sort_asc/desc
     * orderby_comment_count : /index.php/api/Goods/goodsList/id/12/sort/comment_count/sort_asc/desc
     * orderby_is_new : /index.php/api/Goods/goodsList/id/12/sort/is_new/sort_asc/desc
     */

    private String sort;
    private String sort_asc;
    private String orderby_default;
    private String orderby_sales_sum;
    private String orderby_price;
    private String orderby_comment_count;
    private String orderby_is_new;
    private List<GoodsBean> goods_list;
    private List<FilterAttrBean> filter_attr;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort_asc() {
        return sort_asc;
    }

    public void setSort_asc(String sort_asc) {
        this.sort_asc = sort_asc;
    }

    public String getOrderby_default() {
        return orderby_default;
    }

    public void setOrderby_default(String orderby_default) {
        this.orderby_default = orderby_default;
    }

    public String getOrderby_sales_sum() {
        return orderby_sales_sum;
    }

    public void setOrderby_sales_sum(String orderby_sales_sum) {
        this.orderby_sales_sum = orderby_sales_sum;
    }

    public String getOrderby_price() {
        return orderby_price;
    }

    public void setOrderby_price(String orderby_price) {
        this.orderby_price = orderby_price;
    }

    public String getOrderby_comment_count() {
        return orderby_comment_count;
    }

    public void setOrderby_comment_count(String orderby_comment_count) {
        this.orderby_comment_count = orderby_comment_count;
    }

    public String getOrderby_is_new() {
        return orderby_is_new;
    }

    public void setOrderby_is_new(String orderby_is_new) {
        this.orderby_is_new = orderby_is_new;
    }


    public List<FilterAttrBean> getFilter_attr() {
        return filter_attr;
    }

    public void setFilter_attr(List<FilterAttrBean> filter_attr) {
        this.filter_attr = filter_attr;
    }

    public List<GoodsBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class FilterAttrBean {
        /**
         * name : 内存容量
         * item : [{"name":"64G","href":"/index.php/api/Goods/goodsList/id/12/attr/68_64G","key":"attr","value":"68_64G","id":1},{"name":"8G","href":"/index.php/api/Goods/goodsList/id/12/attr/68_8G","key":"attr","value":"68_8G","id":2}]
         */

        private String name;
        private List<ItemBean> item;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            @Override
            public String toString() {
                return "ItemBean{" +
                        "name='" + name + '\'' +
                        ", href='" + href + '\'' +
                        ", key='" + key + '\'' +
                        ", value='" + value + '\'' +
                        ", id=" + id +
                        '}';
            }

            /**
             * name : 64G
             * href : /index.php/api/Goods/goodsList/id/12/attr/68_64G
             * key : attr
             * value : 68_64G
             * id : 1
             */

            private String name;
            private String href;
            private String key;
            private String value;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
