package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2017/12/28.
 */

public class ShoppingCartStoreModel {

    /**
     * store_id : 2
     * store_name : 海澜之家
     * store_logo : /public/upload/seller/2016/10-28/5812c2b8db09b.jpg
     * is_own_shop : 0
     * cartList : [{"id":18,"user_id":51,"session_id":"","goods_id":115,"goods_sn":"TP0000115","goods_name":"爸爸2陆毅代言索扬20000毫安充电宝轻薄正品手机通用移动电源MAh","market_price":"184.90","goods_price":"84.90","member_goods_price":"84.90","goods_num":2,"spec_key":"105","spec_key_name":"颜色:宝石蓝","bar_code":"","selected":1,"add_time":1514433665,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":298},{"id":19,"user_id":51,"session_id":"","goods_id":55,"goods_sn":"TP0000055","goods_name":"华为（HUAWEI）WS832 1200M 11AC双频智能无线路由器（白色）","market_price":"359.00","goods_price":"349.00","member_goods_price":"349.00","goods_num":1,"spec_key":"58","spec_key_name":"颜色:白色","bar_code":"","selected":1,"add_time":1514433677,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":21,"user_id":51,"session_id":"","goods_id":88,"goods_sn":"TP0000088","goods_name":"香奈儿 /CHANEL 粉色邂逅柔情女士持久淡香水 35ML 法国进口","market_price":"390.00","goods_price":"290.00","member_goods_price":"290.00","goods_num":1,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514436996,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":22,"user_id":51,"session_id":"","goods_id":70,"goods_sn":"TP0000070","goods_name":"布雷尔 皮床 双人床软体床真皮床 皮艺床软床1.8米软包双人床婚床","market_price":"1899.00","goods_price":"1799.00","member_goods_price":"1799.00","goods_num":2,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514440223,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":23,"user_id":51,"session_id":"","goods_id":72,"goods_sn":"TP0000072","goods_name":"天堂3311E碰强力高密拒水碰击布一甩干三折晴雨伞深藏青","market_price":"128.90","goods_price":"28.90","member_goods_price":"28.90","goods_num":4,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442376,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":24,"user_id":51,"session_id":"","goods_id":65,"goods_sn":"TP0000065","goods_name":"长虹(CHANGHONG) 49A1U 49英寸双64位4K超清智能网络LED液晶电视","market_price":"2899.00","goods_price":"2799.00","member_goods_price":"2799.00","goods_num":6,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442763,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":25,"user_id":51,"session_id":"","goods_id":90,"goods_sn":"TP0000090","goods_name":"迪奥迪奥小 姐花漾淡香氛5ml","market_price":"6100.00","goods_price":"6000.00","member_goods_price":"6000.00","goods_num":12,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514442960,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":200},{"id":26,"user_id":51,"session_id":"","goods_id":73,"goods_sn":"TP0000073","goods_name":"天堂339S格高密隐格聚酯纺三折晴雨伞 F藏青","market_price":"118.90","goods_price":"18.90","member_goods_price":"18.90","goods_num":19,"spec_key":"","spec_key_name":"","bar_code":"","selected":1,"add_time":1514443984,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100},{"id":27,"user_id":51,"session_id":"","goods_id":86,"goods_sn":"TP0000086","goods_name":"艾霏娅 2015秋季新款女装 欧美时尚气质大码A字型宽松毛呢大衣外套 女","market_price":"975.00","goods_price":"875.00","member_goods_price":"875.00","goods_num":1,"spec_key":"79","spec_key_name":"尺码:L","bar_code":"","selected":1,"add_time":1514444496,"prom_type":0,"prom_id":0,"sku":"","store_id":2,"prom_goods":null,"store_count":100}]
     */
    private int store_id;
    private String store_name;
    private String store_logo;
    private String is_own_shop;
    private List<ShoppingCartStoreGoodsModel> cartList;


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

    public List<ShoppingCartStoreGoodsModel> getCartList() {
        return cartList;
    }

    public void setCartList(List<ShoppingCartStoreGoodsModel> cartList) {
        this.cartList = cartList;
    }
}
