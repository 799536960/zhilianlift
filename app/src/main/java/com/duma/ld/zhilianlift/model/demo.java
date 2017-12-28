package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/28.
 */

public class demo {

    /**
     * total_price : {"total_fee":94650.5,"cut_fee":0,"num":49}
     * storeList : [{"store_id":1,"store_name":"智联生活商城","store_logo":"","is_own_shop":"","cartList":[{"id":18,"user_id":51,"session_id":"","goods_id":115,"goods_sn":"TP0000115","goods_name":"爸爸2陆毅代言索扬20000毫安充电宝轻薄正品手机通用移动电源MAh","market_price":"184.90","goods_price":"84.90","member_goods_price":"84.90","goods_num":2,"spec_key":"105","spec_key_name":"颜色:宝石蓝","bar_code":"","selected":1,"add_time":1514433665,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":298},{"id":19,"user_id":51,"session_id":"","goods_id":55,"goods_sn":"TP0000055","goods_name":"华为（HUAWEI）WS832 1200M 11AC双频智能无线路由器（白色）","market_price":"359.00","goods_price":"349.00","member_goods_price":"349.00","goods_num":1,"spec_key":"58","spec_key_name":"颜色:白色","bar_code":"","selected":1,"add_time":1514433677,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":20,"user_id":51,"session_id":"","goods_id":164,"goods_sn":"yunda","goods_name":"荣耀7 双卡双待双通 移动4G版 16GB存储（冰河银）豪华套装一","market_price":"12.00","goods_price":"100.00","member_goods_price":"100.00","goods_num":1,"spec_key":"173_175_178_179_184","spec_key_name":"颜色:67_黑 希望:一点希望 长度:89 就是就:有点难 我也不知道:是什么","bar_code":"","selected":1,"add_time":1514433693,"prom_type":0,"prom_id":0,"sku":"782","store_id":7,"prom_goods":null,"store_count":8069},{"id":21,"user_id":51,"session_id":"","goods_id":88,"goods_sn":"TP0000088","goods_name":"香奈儿 /CHANEL 粉色邂逅柔情女士持久淡香水 35ML 法国进口","market_price":"390.00","goods_price":"290.00","member_goods_price":"290.00","goods_num":1,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514436996,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":22,"user_id":51,"session_id":"","goods_id":70,"goods_sn":"TP0000070","goods_name":"布雷尔 皮床 双人床软体床真皮床 皮艺床软床1.8米软包双人床婚床","market_price":"1899.00","goods_price":"1799.00","member_goods_price":"1799.00","goods_num":2,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514440223,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":23,"user_id":51,"session_id":"","goods_id":72,"goods_sn":"TP0000072","goods_name":"天堂3311E碰强力高密拒水碰击布一甩干三折晴雨伞深藏青","market_price":"128.90","goods_price":"28.90","member_goods_price":"28.90","goods_num":4,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442376,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":24,"user_id":51,"session_id":"","goods_id":65,"goods_sn":"TP0000065","goods_name":"长虹(CHANGHONG) 49A1U 49英寸双64位4K超清智能网络LED液晶电视","market_price":"2899.00","goods_price":"2799.00","member_goods_price":"2799.00","goods_num":6,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442763,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":25,"user_id":51,"session_id":"","goods_id":90,"goods_sn":"TP0000090","goods_name":"迪奥迪奥小 姐花漾淡香氛5ml","market_price":"6100.00","goods_price":"6000.00","member_goods_price":"6000.00","goods_num":12,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442960,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":200},{"id":26,"user_id":51,"session_id":"","goods_id":73,"goods_sn":"TP0000073","goods_name":"天堂339S格高密隐格聚酯纺三折晴雨伞 F藏青","market_price":"118.90","goods_price":"18.90","member_goods_price":"18.90","goods_num":19,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514443984,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":27,"user_id":51,"session_id":"","goods_id":86,"goods_sn":"TP0000086","goods_name":"艾霏娅 2015秋季新款女装 欧美时尚气质大码A字型宽松毛呢大衣外套 女","market_price":"975.00","goods_price":"875.00","member_goods_price":"875.00","goods_num":1,"spec_key":"79","spec_key_name":"尺码:L","bar_code":"","selected":1,"add_time":1514444496,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100}]}]
     */

    private TotalPriceBean total_price;
    private List<StoreListBean> storeList;

    public TotalPriceBean getTotal_price() {
        return total_price;
    }

    public void setTotal_price(TotalPriceBean total_price) {
        this.total_price = total_price;
    }

    public List<StoreListBean> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreListBean> storeList) {
        this.storeList = storeList;
    }

    public static class TotalPriceBean {
        /**
         * total_fee : 94650.5
         * cut_fee : 0
         * num : 49
         */

        private double total_fee;
        private int cut_fee;
        private int num;

        public double getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(double total_fee) {
            this.total_fee = total_fee;
        }

        public int getCut_fee() {
            return cut_fee;
        }

        public void setCut_fee(int cut_fee) {
            this.cut_fee = cut_fee;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class StoreListBean {
        /**
         * store_id : 1
         * store_name : 智联生活商城
         * store_logo :
         * is_own_shop :
         * cartList : [{"id":18,"user_id":51,"session_id":"","goods_id":115,"goods_sn":"TP0000115","goods_name":"爸爸2陆毅代言索扬20000毫安充电宝轻薄正品手机通用移动电源MAh","market_price":"184.90","goods_price":"84.90","member_goods_price":"84.90","goods_num":2,"spec_key":"105","spec_key_name":"颜色:宝石蓝","bar_code":"","selected":1,"add_time":1514433665,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":298},{"id":19,"user_id":51,"session_id":"","goods_id":55,"goods_sn":"TP0000055","goods_name":"华为（HUAWEI）WS832 1200M 11AC双频智能无线路由器（白色）","market_price":"359.00","goods_price":"349.00","member_goods_price":"349.00","goods_num":1,"spec_key":"58","spec_key_name":"颜色:白色","bar_code":"","selected":1,"add_time":1514433677,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":20,"user_id":51,"session_id":"","goods_id":164,"goods_sn":"yunda","goods_name":"荣耀7 双卡双待双通 移动4G版 16GB存储（冰河银）豪华套装一","market_price":"12.00","goods_price":"100.00","member_goods_price":"100.00","goods_num":1,"spec_key":"173_175_178_179_184","spec_key_name":"颜色:67_黑 希望:一点希望 长度:89 就是就:有点难 我也不知道:是什么","bar_code":"","selected":1,"add_time":1514433693,"prom_type":0,"prom_id":0,"sku":"782","store_id":7,"prom_goods":null,"store_count":8069},{"id":21,"user_id":51,"session_id":"","goods_id":88,"goods_sn":"TP0000088","goods_name":"香奈儿 /CHANEL 粉色邂逅柔情女士持久淡香水 35ML 法国进口","market_price":"390.00","goods_price":"290.00","member_goods_price":"290.00","goods_num":1,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514436996,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":22,"user_id":51,"session_id":"","goods_id":70,"goods_sn":"TP0000070","goods_name":"布雷尔 皮床 双人床软体床真皮床 皮艺床软床1.8米软包双人床婚床","market_price":"1899.00","goods_price":"1799.00","member_goods_price":"1799.00","goods_num":2,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514440223,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":23,"user_id":51,"session_id":"","goods_id":72,"goods_sn":"TP0000072","goods_name":"天堂3311E碰强力高密拒水碰击布一甩干三折晴雨伞深藏青","market_price":"128.90","goods_price":"28.90","member_goods_price":"28.90","goods_num":4,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442376,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":24,"user_id":51,"session_id":"","goods_id":65,"goods_sn":"TP0000065","goods_name":"长虹(CHANGHONG) 49A1U 49英寸双64位4K超清智能网络LED液晶电视","market_price":"2899.00","goods_price":"2799.00","member_goods_price":"2799.00","goods_num":6,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442763,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":25,"user_id":51,"session_id":"","goods_id":90,"goods_sn":"TP0000090","goods_name":"迪奥迪奥小 姐花漾淡香氛5ml","market_price":"6100.00","goods_price":"6000.00","member_goods_price":"6000.00","goods_num":12,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442960,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":200},{"id":26,"user_id":51,"session_id":"","goods_id":73,"goods_sn":"TP0000073","goods_name":"天堂339S格高密隐格聚酯纺三折晴雨伞 F藏青","market_price":"118.90","goods_price":"18.90","member_goods_price":"18.90","goods_num":19,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514443984,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":27,"user_id":51,"session_id":"","goods_id":86,"goods_sn":"TP0000086","goods_name":"艾霏娅 2015秋季新款女装 欧美时尚气质大码A字型宽松毛呢大衣外套 女","market_price":"975.00","goods_price":"875.00","member_goods_price":"875.00","goods_num":1,"spec_key":"79","spec_key_name":"尺码:L","bar_code":"","selected":1,"add_time":1514444496,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100}]
         */

        private int store_id;
        private String store_name;
        private String store_logo;
        private String is_own_shop;
        private List<CartListBean> cartList;

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

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getIs_own_shop() {
            return is_own_shop;
        }

        public void setIs_own_shop(String is_own_shop) {
            this.is_own_shop = is_own_shop;
        }

        public List<CartListBean> getCartList() {
            return cartList;
        }

        public void setCartList(List<CartListBean> cartList) {
            this.cartList = cartList;
        }

        public static class CartListBean {
            /**
             * id : 18
             * user_id : 51
             * session_id :
             * goods_id : 115
             * goods_sn : TP0000115
             * goods_name : 爸爸2陆毅代言索扬20000毫安充电宝轻薄正品手机通用移动电源MAh
             * market_price : 184.90
             * goods_price : 84.90
             * member_goods_price : 84.90
             * goods_num : 2
             * spec_key : 105
             * spec_key_name : 颜色:宝石蓝
             * bar_code :
             * selected : 1
             * add_time : 1514433665
             * prom_type : 0
             * prom_id : 0
             * sku :
             * store_id : 2
             * prom_goods : null
             * store_count : 298
             */

            private int id;
            private int user_id;
            private String session_id;
            private int goods_id;
            private String goods_sn;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String member_goods_price;
            private int goods_num;
            private String spec_key;
            private String spec_key_name;
            private String bar_code;
            private int selected;
            private int add_time;
            private int prom_type;
            private int prom_id;
            private String sku;
            private int store_id;
            private Object prom_goods;
            private int store_count;

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

            public String getSession_id() {
                return session_id;
            }

            public void setSession_id(String session_id) {
                this.session_id = session_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getMember_goods_price() {
                return member_goods_price;
            }

            public void setMember_goods_price(String member_goods_price) {
                this.member_goods_price = member_goods_price;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getSpec_key() {
                return spec_key;
            }

            public void setSpec_key(String spec_key) {
                this.spec_key = spec_key;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getBar_code() {
                return bar_code;
            }

            public void setBar_code(String bar_code) {
                this.bar_code = bar_code;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
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

            public Object getProm_goods() {
                return prom_goods;
            }

            public void setProm_goods(Object prom_goods) {
                this.prom_goods = prom_goods;
            }

            public int getStore_count() {
                return store_count;
            }

            public void setStore_count(int store_count) {
                this.store_count = store_count;
            }
        }
    }
}
