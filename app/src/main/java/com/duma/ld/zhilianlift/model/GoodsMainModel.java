package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/22.
 */

public class GoodsMainModel {

    private ActivityBean activity;
    private GoodsBean goods;
    private StatisticsBean statistics;
    private ConsigneeBean consignee;
    private List<GoodsSpecListBean> goods_spec_list;
    private List<SpecGoodsPriceBean> spec_goods_price;
    private List<GalleryBean> gallery;
    private List<CommentModel> comment;
    private StoreModel store;

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }

    public ActivityBean getActivity() {
        return activity;
    }

    public void setActivity(ActivityBean activity) {
        this.activity = activity;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }


    public StatisticsBean getStatistics() {
        return statistics;
    }

    public void setStatistics(StatisticsBean statistics) {
        this.statistics = statistics;
    }

    public ConsigneeBean getConsignee() {
        return consignee;
    }

    public void setConsignee(ConsigneeBean consignee) {
        this.consignee = consignee;
    }

    public List<GoodsSpecListBean> getGoods_spec_list() {
        return goods_spec_list;
    }

    public void setGoods_spec_list(List<GoodsSpecListBean> goods_spec_list) {
        this.goods_spec_list = goods_spec_list;
    }

    public List<SpecGoodsPriceBean> getSpec_goods_price() {
        return spec_goods_price;
    }

    public void setSpec_goods_price(List<SpecGoodsPriceBean> spec_goods_price) {
        this.spec_goods_price = spec_goods_price;
    }

    public List<GalleryBean> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryBean> gallery) {
        this.gallery = gallery;
    }

    public List<CommentModel> getComment() {
        return comment;
    }

    public void setComment(List<CommentModel> comment) {
        this.comment = comment;
    }

    public static class ActivityBean {
        /**
         * prom_type : 0
         * server_current_time : 1513923742
         */

        private int prom_type;
        private int server_current_time;

        public int getProm_type() {
            return prom_type;
        }

        public void setProm_type(int prom_type) {
            this.prom_type = prom_type;
        }

        public int getServer_current_time() {
            return server_current_time;
        }

        public void setServer_current_time(int server_current_time) {
            this.server_current_time = server_current_time;
        }
    }

    public static class GoodsBean {
        /**
         * goods_id : 121
         * cat_id1 : 1
         * cat_id2 : 12
         * cat_id3 : 100
         * store_cat_id1 : 0
         * store_cat_id2 : 0
         * goods_sn : TP0000121
         * goods_name : 科智50000通用充电宝20000毫安冲手机蘋果6s超薄可爱便携移动电源
         * click_count : 2
         * brand_id : 0
         * store_count : 100
         * collect_sum : 0
         * comment_count : 0
         * weight : 0
         * market_price : 169.90
         * shop_price : 69.90
         * cost_price : 0.00
         * exchange_integral : 0
         * keywords :
         * goods_remark :
         * original_img : /public/upload/goods/2016/04-21/571837b30942a.jpg
         * is_virtual : 0
         * virtual_indate : 0
         * virtual_limit : 0
         * virtual_refund : 1
         * is_on_sale : 1
         * is_free_shipping : 0
         * on_time : 1461204949
         * sort : 50
         * is_recommend : 0
         * is_new : 0
         * is_hot : 1
         * last_update : 0
         * goods_type : 31
         * give_integral : 0
         * sales_sum : 0
         * prom_type : 0
         * prom_id : 0
         * distribut : 0.00
         * store_id : 2
         * spu :
         * sku :
         * goods_state : 1
         * close_reason : null
         * suppliers_id : null
         * shipping_area_ids :
         * is_own_shop : 0
         * province_id : 11
         * city_id : 123
         * district : 1253
         * province_code : 330000
         * city_code : 330100
         * district_code : 330108
         * is_collect : 0
         * point_rate : 100
         */

        private int goods_id;
        private int cat_id1;
        private int cat_id2;
        private int cat_id3;
        private int store_cat_id1;
        private int store_cat_id2;
        private String goods_sn;
        private String goods_name;
        private int click_count;
        private int brand_id;
        private int store_count;
        private int collect_sum;
        private int comment_count;
        private int weight;
        private String market_price;
        private String shop_price;
        private String cost_price;
        private int exchange_integral;
        private String keywords;
        private String goods_remark;
        private String original_img;
        private int is_virtual;
        private int virtual_indate;
        private int virtual_limit;
        private int virtual_refund;
        private int is_on_sale;
        private int is_free_shipping;
        private int on_time;
        private int sort;
        private int is_recommend;
        private int is_new;
        private int is_hot;
        private int last_update;
        private int goods_type;
        private int give_integral;
        private int sales_sum;
        private int prom_type;
        private int prom_id;
        private String distribut;
        private int store_id;
        private String spu;
        private String sku;
        private int goods_state;
        private Object close_reason;
        private Object suppliers_id;
        private String shipping_area_ids;
        private int is_own_shop;
        private int province_id;
        private int city_id;
        private int district;
        private String province_code;
        private String city_code;
        private String district_code;
        private int is_collect;
        private String point_rate;
        private String good_comment_rate;

        public String getGood_comment_rate() {
            return good_comment_rate;
        }

        public void setGood_comment_rate(String good_comment_rate) {
            this.good_comment_rate = good_comment_rate;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getCat_id1() {
            return cat_id1;
        }

        public void setCat_id1(int cat_id1) {
            this.cat_id1 = cat_id1;
        }

        public int getCat_id2() {
            return cat_id2;
        }

        public void setCat_id2(int cat_id2) {
            this.cat_id2 = cat_id2;
        }

        public int getCat_id3() {
            return cat_id3;
        }

        public void setCat_id3(int cat_id3) {
            this.cat_id3 = cat_id3;
        }

        public int getStore_cat_id1() {
            return store_cat_id1;
        }

        public void setStore_cat_id1(int store_cat_id1) {
            this.store_cat_id1 = store_cat_id1;
        }

        public int getStore_cat_id2() {
            return store_cat_id2;
        }

        public void setStore_cat_id2(int store_cat_id2) {
            this.store_cat_id2 = store_cat_id2;
        }

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getClick_count() {
            return click_count;
        }

        public void setClick_count(int click_count) {
            this.click_count = click_count;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        public int getCollect_sum() {
            return collect_sum;
        }

        public void setCollect_sum(int collect_sum) {
            this.collect_sum = collect_sum;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }

        public int getExchange_integral() {
            return exchange_integral;
        }

        public void setExchange_integral(int exchange_integral) {
            this.exchange_integral = exchange_integral;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getGoods_remark() {
            return goods_remark;
        }

        public void setGoods_remark(String goods_remark) {
            this.goods_remark = goods_remark;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public int getIs_virtual() {
            return is_virtual;
        }

        public void setIs_virtual(int is_virtual) {
            this.is_virtual = is_virtual;
        }

        public int getVirtual_indate() {
            return virtual_indate;
        }

        public void setVirtual_indate(int virtual_indate) {
            this.virtual_indate = virtual_indate;
        }

        public int getVirtual_limit() {
            return virtual_limit;
        }

        public void setVirtual_limit(int virtual_limit) {
            this.virtual_limit = virtual_limit;
        }

        public int getVirtual_refund() {
            return virtual_refund;
        }

        public void setVirtual_refund(int virtual_refund) {
            this.virtual_refund = virtual_refund;
        }

        public int getIs_on_sale() {
            return is_on_sale;
        }

        public void setIs_on_sale(int is_on_sale) {
            this.is_on_sale = is_on_sale;
        }

        public int getIs_free_shipping() {
            return is_free_shipping;
        }

        public void setIs_free_shipping(int is_free_shipping) {
            this.is_free_shipping = is_free_shipping;
        }

        public int getOn_time() {
            return on_time;
        }

        public void setOn_time(int on_time) {
            this.on_time = on_time;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public int getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(int is_hot) {
            this.is_hot = is_hot;
        }

        public int getLast_update() {
            return last_update;
        }

        public void setLast_update(int last_update) {
            this.last_update = last_update;
        }

        public int getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(int goods_type) {
            this.goods_type = goods_type;
        }

        public int getGive_integral() {
            return give_integral;
        }

        public void setGive_integral(int give_integral) {
            this.give_integral = give_integral;
        }

        public int getSales_sum() {
            return sales_sum;
        }

        public void setSales_sum(int sales_sum) {
            this.sales_sum = sales_sum;
        }

        public int getProm_type() {
            return prom_type;
        }

        public void setProm_type(int prom_type) {
            this.prom_type = prom_type;
        }

        public int getProm_id() {
            return prom_id;
        }

        public void setProm_id(int prom_id) {
            this.prom_id = prom_id;
        }

        public String getDistribut() {
            return distribut;
        }

        public void setDistribut(String distribut) {
            this.distribut = distribut;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getSpu() {
            return spu;
        }

        public void setSpu(String spu) {
            this.spu = spu;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public int getGoods_state() {
            return goods_state;
        }

        public void setGoods_state(int goods_state) {
            this.goods_state = goods_state;
        }

        public Object getClose_reason() {
            return close_reason;
        }

        public void setClose_reason(Object close_reason) {
            this.close_reason = close_reason;
        }

        public Object getSuppliers_id() {
            return suppliers_id;
        }

        public void setSuppliers_id(Object suppliers_id) {
            this.suppliers_id = suppliers_id;
        }

        public String getShipping_area_ids() {
            return shipping_area_ids;
        }

        public void setShipping_area_ids(String shipping_area_ids) {
            this.shipping_area_ids = shipping_area_ids;
        }

        public int getIs_own_shop() {
            return is_own_shop;
        }

        public void setIs_own_shop(int is_own_shop) {
            this.is_own_shop = is_own_shop;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public int getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(int is_collect) {
            this.is_collect = is_collect;
        }

        public String getPoint_rate() {
            return point_rate;
        }

        public void setPoint_rate(String point_rate) {
            this.point_rate = point_rate;
        }
    }

    public static class StatisticsBean {
        /**
         * c0 : 3
         * c1 : 1
         * c2 : 1
         * c3 : 1
         * c4 : 1
         * rate1 : 34
         * rate2 : 34
         * rate3 : 34
         */

        private int c0;
        private int c1;
        private int c2;
        private int c3;
        private int c4;
        private int rate1;
        private int rate2;
        private int rate3;

        public int getC0() {
            return c0;
        }

        public void setC0(int c0) {
            this.c0 = c0;
        }

        public int getC1() {
            return c1;
        }

        public void setC1(int c1) {
            this.c1 = c1;
        }

        public int getC2() {
            return c2;
        }

        public void setC2(int c2) {
            this.c2 = c2;
        }

        public int getC3() {
            return c3;
        }

        public void setC3(int c3) {
            this.c3 = c3;
        }

        public int getC4() {
            return c4;
        }

        public void setC4(int c4) {
            this.c4 = c4;
        }

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
    }

    public static class ConsigneeBean {
        /**
         * address_id : 0
         * address : 请选择收货人
         * district : 0
         */

        private int address_id;
        private String address;
        private int district;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }
    }

    public static class GoodsSpecListBean {
        /**
         * spec_name : 颜色
         * spec_list : [{"item_id":104,"item":"象牙白","src":null}]
         */

        private String spec_name;
        private List<SpecListBean> spec_list;

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public List<SpecListBean> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<SpecListBean> spec_list) {
            this.spec_list = spec_list;
        }

        public static class SpecListBean {
            /**
             * item_id : 104
             * item : 象牙白
             * src : null
             */

            private int item_id;
            private String item;
            private Object src;

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public Object getSrc() {
                return src;
            }

            public void setSrc(Object src) {
                this.src = src;
            }
        }
    }

    public static class SpecGoodsPriceBean {
        /**
         * item_id : 154
         * goods_id : 121
         * goods_mark : null
         * key : 104
         * key_name : 象牙白
         * price : 69.90
         * store_count : 100
         * bar_code :
         * sku :
         * store_id : 2
         * spec_img : null
         * prom_id : 0
         * prom_type : 0
         */

        private int item_id;
        private int goods_id;
        private String goods_mark;
        private String key;
        private String key_name;
        private String price;
        private int store_count;
        private String bar_code;
        private String sku;
        private int store_id;
        private String spec_img;
        private int prom_id;
        private int prom_type;

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }


        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getStore_count() {
            return store_count;
        }

        public void setStore_count(int store_count) {
            this.store_count = store_count;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getSpec_img() {
            return spec_img;
        }

        public void setSpec_img(String spec_img) {
            this.spec_img = spec_img;
        }

        public String getGoods_mark() {
            return goods_mark;
        }

        public void setGoods_mark(String goods_mark) {
            this.goods_mark = goods_mark;
        }

        public int getProm_id() {
            return prom_id;
        }

        public void setProm_id(int prom_id) {
            this.prom_id = prom_id;
        }

        public int getProm_type() {
            return prom_type;
        }

        public void setProm_type(int prom_type) {
            this.prom_type = prom_type;
        }
    }

    public static class GalleryBean {
        /**
         * image_url : http://192.168.0.58:8081/public/upload/goods/2016/04-21/5718379d770a2.jpg
         */

        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

}
