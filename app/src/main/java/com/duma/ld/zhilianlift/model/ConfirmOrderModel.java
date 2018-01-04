package com.duma.ld.zhilianlift.model;

import java.util.List;

/**
 * Created by liudong on 2018/1/4.
 */

public class ConfirmOrderModel {

    /**
     * addressList : {"address_id":37,"user_id":50,"consignee":"张凯","email":"","country":0,"province":11,"city":123,"district":1253,"twon":0,"address":"浙江省杭州市滨江区长江西苑","zipcode":"","mobile":"17055916591","is_default":1,"total_address":"浙江省杭州市滨江区浙江省杭州市滨江区长江西苑"}
     * couponNum : [{"id":165,"uid":50,"cid":29,"use_start_time":1512118877,"use_end_time":1519894877,"name":"满20减20","money":"20.00","condition":"20.00"},{"id":166,"uid":50,"cid":29,"use_start_time":1512118877,"use_end_time":1519894877,"name":"满20减20","money":"20.00","condition":"20.00"}]
     * cartGoodsTotalNum : 1
     * userInfo : {"user_id":50,"email":"888888","password":"519475228fe35ad067744465c42a19b2","sex":3,"birthday":1512835200,"user_money":"100464.70","frozen_money":"0.00","distribut_money":"0.00","pay_points":1200,"paypwd":"519475228fe35ad067744465c42a19b2","reg_time":1512541024,"last_login":1515032440,"last_ip":"","qq":"","mobile":"17055916591","mobile_validated":1,"oauth":"","openid":null,"unionid":null,"head_pic":"/public/upload/head_pic/20171221/5a2dd8ee1a634696c25942fe7b5e18bb.png","bank_name":null,"bank_card":null,"realname":"zhangkaisssss","idcard":"421127199511061733","email_validated":0,"nickname":"zhangsan","level":1,"discount":"1.00","total_amount":"316.80","is_lock":0,"is_distribut":1,"first_leader":0,"second_leader":0,"third_leader":0,"token":"8cd466d0b1e358e1022d54c19a50e39e","underling_number":0,"message_mask":63,"push_id":"","renovation_money":"2000.00","certification_is":1}
     * list : {"store_id":1,"store_name":"智联生活商城","store_logo":"","is_own_shop":"","cartList":[{"user_id":50,"session_id":"cvo2lijnu5d7rrk6f5u9l1see4","goods_id":164,"goods_sn":"yunda","goods_name":"荣耀7 双卡双待双通 移动4G版 16GB存储（冰河银）豪华套装一","market_price":"12.00","goods_price":"100.00","member_goods_price":"100.00","goods_num":1,"add_time":1515032440,"prom_type":0,"prom_id":0,"store_id":7,"weight":"10","spec_key":"173_175_178_179_183","spec_key_name":"颜色:67_黑 希望:一点希望 长度:89 就是就:有点难 我也不知道:这个","sku":"782","cut_fee":0,"goods_fee":100,"total_fee":100,"cat_id3":850,"prom_title":""}]}
     * storeCartTotalPrice : 100
     * shipping_price : 11
     * coupon_price : 20
     * order_amount : 91
     */

    private AddressListBean addressList;
    private int cartGoodsTotalNum;
    private UserInfoBean userInfo;
    private ListBean list;
    private String storeCartTotalPrice;
    private String last_order_amount;
    private String shipping_price;
    private int coupon_price;
    private String order_amount;
    private List<CouponNumBean> couponNum;

    public String getLast_order_amount() {
        return last_order_amount;
    }

    public void setLast_order_amount(String last_order_amount) {
        this.last_order_amount = last_order_amount;
    }

    public AddressListBean getAddressList() {
        return addressList;
    }

    public void setAddressList(AddressListBean addressList) {
        this.addressList = addressList;
    }

    public int getCartGoodsTotalNum() {
        return cartGoodsTotalNum;
    }

    public void setCartGoodsTotalNum(int cartGoodsTotalNum) {
        this.cartGoodsTotalNum = cartGoodsTotalNum;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }


    public int getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(int coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getStoreCartTotalPrice() {
        return storeCartTotalPrice;
    }

    public void setStoreCartTotalPrice(String storeCartTotalPrice) {
        this.storeCartTotalPrice = storeCartTotalPrice;
    }

    public String getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(String shipping_price) {
        this.shipping_price = shipping_price;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public List<CouponNumBean> getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(List<CouponNumBean> couponNum) {
        this.couponNum = couponNum;
    }

    public static class AddressListBean {
        /**
         * address_id : 37
         * user_id : 50
         * consignee : 张凯
         * email :
         * country : 0
         * province : 11
         * city : 123
         * district : 1253
         * twon : 0
         * address : 浙江省杭州市滨江区长江西苑
         * zipcode :
         * mobile : 17055916591
         * is_default : 1
         * total_address : 浙江省杭州市滨江区浙江省杭州市滨江区长江西苑
         */

        private int address_id;
        private int user_id;
        private String consignee;
        private String email;
        private int country;
        private int province;
        private int city;
        private int district;
        private int twon;
        private String address;
        private String zipcode;
        private String mobile;
        private int is_default;
        private String total_address;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public int getTwon() {
            return twon;
        }

        public void setTwon(int twon) {
            this.twon = twon;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getTotal_address() {
            return total_address;
        }

        public void setTotal_address(String total_address) {
            this.total_address = total_address;
        }
    }

    public static class UserInfoBean {
        /**
         * user_id : 50
         * email : 888888
         * password : 519475228fe35ad067744465c42a19b2
         * sex : 3
         * birthday : 1512835200
         * user_money : 100464.70
         * frozen_money : 0.00
         * distribut_money : 0.00
         * pay_points : 1200
         * paypwd : 519475228fe35ad067744465c42a19b2
         * reg_time : 1512541024
         * last_login : 1515032440
         * last_ip :
         * qq :
         * mobile : 17055916591
         * mobile_validated : 1
         * oauth :
         * openid : null
         * unionid : null
         * head_pic : /public/upload/head_pic/20171221/5a2dd8ee1a634696c25942fe7b5e18bb.png
         * bank_name : null
         * bank_card : null
         * realname : zhangkaisssss
         * idcard : 421127199511061733
         * email_validated : 0
         * nickname : zhangsan
         * level : 1
         * discount : 1.00
         * total_amount : 316.80
         * is_lock : 0
         * is_distribut : 1
         * first_leader : 0
         * second_leader : 0
         * third_leader : 0
         * token : 8cd466d0b1e358e1022d54c19a50e39e
         * underling_number : 0
         * message_mask : 63
         * push_id :
         * renovation_money : 2000.00
         * certification_is : 1
         */

        private int user_id;
        private String email;
        private String password;
        private int sex;
        private int birthday;
        private String user_money;
        private String frozen_money;
        private String distribut_money;
        private String pay_points;
        private String paypwd;
        private int reg_time;
        private int last_login;
        private String last_ip;
        private String qq;
        private String mobile;
        private int mobile_validated;
        private String oauth;
        private String openid;
        private String unionid;
        private String head_pic;
        private String bank_name;
        private String bank_card;
        private String realname;
        private String idcard;
        private int email_validated;
        private String nickname;
        private int level;
        private String discount;
        private String total_amount;
        private int is_lock;
        private int is_distribut;
        private int first_leader;
        private int second_leader;
        private int third_leader;
        private String token;
        private int underling_number;
        private int message_mask;
        private String push_id;
        private String renovation_money;
        private int certification_is;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }


        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getDistribut_money() {
            return distribut_money;
        }

        public void setDistribut_money(String distribut_money) {
            this.distribut_money = distribut_money;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getPaypwd() {
            return paypwd;
        }

        public void setPaypwd(String paypwd) {
            this.paypwd = paypwd;
        }

        public int getReg_time() {
            return reg_time;
        }

        public void setReg_time(int reg_time) {
            this.reg_time = reg_time;
        }

        public int getLast_login() {
            return last_login;
        }

        public void setLast_login(int last_login) {
            this.last_login = last_login;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMobile_validated() {
            return mobile_validated;
        }

        public void setMobile_validated(int mobile_validated) {
            this.mobile_validated = mobile_validated;
        }

        public String getOauth() {
            return oauth;
        }

        public void setOauth(String oauth) {
            this.oauth = oauth;
        }


        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getEmail_validated() {
            return email_validated;
        }

        public void setEmail_validated(int email_validated) {
            this.email_validated = email_validated;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public int getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(int is_lock) {
            this.is_lock = is_lock;
        }

        public int getIs_distribut() {
            return is_distribut;
        }

        public void setIs_distribut(int is_distribut) {
            this.is_distribut = is_distribut;
        }

        public int getFirst_leader() {
            return first_leader;
        }

        public void setFirst_leader(int first_leader) {
            this.first_leader = first_leader;
        }

        public int getSecond_leader() {
            return second_leader;
        }

        public void setSecond_leader(int second_leader) {
            this.second_leader = second_leader;
        }

        public int getThird_leader() {
            return third_leader;
        }

        public void setThird_leader(int third_leader) {
            this.third_leader = third_leader;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUnderling_number() {
            return underling_number;
        }

        public void setUnderling_number(int underling_number) {
            this.underling_number = underling_number;
        }

        public int getMessage_mask() {
            return message_mask;
        }

        public void setMessage_mask(int message_mask) {
            this.message_mask = message_mask;
        }

        public String getPush_id() {
            return push_id;
        }

        public void setPush_id(String push_id) {
            this.push_id = push_id;
        }

        public String getRenovation_money() {
            return renovation_money;
        }

        public void setRenovation_money(String renovation_money) {
            this.renovation_money = renovation_money;
        }

        public int getCertification_is() {
            return certification_is;
        }

        public void setCertification_is(int certification_is) {
            this.certification_is = certification_is;
        }
    }

    public static class ListBean {
        /**
         * store_id : 1
         * store_name : 智联生活商城
         * store_logo :
         * is_own_shop :
         * cartList : [{"user_id":50,"session_id":"cvo2lijnu5d7rrk6f5u9l1see4","goods_id":164,"goods_sn":"yunda","goods_name":"荣耀7 双卡双待双通 移动4G版 16GB存储（冰河银）豪华套装一","market_price":"12.00","goods_price":"100.00","member_goods_price":"100.00","goods_num":1,"add_time":1515032440,"prom_type":0,"prom_id":0,"store_id":7,"weight":"10","spec_key":"173_175_178_179_183","spec_key_name":"颜色:67_黑 希望:一点希望 长度:89 就是就:有点难 我也不知道:这个","sku":"782","cut_fee":0,"goods_fee":100,"total_fee":100,"cat_id3":850,"prom_title":""}]
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
             * user_id : 50
             * session_id : cvo2lijnu5d7rrk6f5u9l1see4
             * goods_id : 121
             * goods_sn : TP0000121
             * goods_name : 科智50000通用充电宝20000毫安冲手机蘋果6s超薄可爱便携移动电源
             * market_price : 169.90
             * goods_price : 69.90
             * original_img : /public/upload/goods/2016/04-21/571837b30942a.jpg
             * member_goods_price : 69.90
             * goods_num : 1
             * add_time : 1515048240
             * prom_type : 0
             * prom_id : 0
             * store_id : 2
             * weight : 0
             * spec_key : 104
             * spec_key_name : 颜色:象牙白
             * sku :
             * cut_fee : 0
             * goods_fee : 69.9
             * total_fee : 69.9
             * cat_id3 : 100
             * prom_title :
             */

            private int user_id;
            private String session_id;
            private int goods_id;
            private String goods_sn;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String original_img;
            private String member_goods_price;
            private int goods_num;
            private int add_time;
            private int prom_type;
            private int prom_id;
            private int store_id;
            private String weight;
            private String spec_key;
            private String spec_key_name;
            private String sku;
            private int cut_fee;
            private double goods_fee;
            private double total_fee;
            private int cat_id3;
            private String prom_title;

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

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
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

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
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

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public int getCut_fee() {
                return cut_fee;
            }

            public void setCut_fee(int cut_fee) {
                this.cut_fee = cut_fee;
            }

            public double getGoods_fee() {
                return goods_fee;
            }

            public void setGoods_fee(double goods_fee) {
                this.goods_fee = goods_fee;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public int getCat_id3() {
                return cat_id3;
            }

            public void setCat_id3(int cat_id3) {
                this.cat_id3 = cat_id3;
            }

            public String getProm_title() {
                return prom_title;
            }

            public void setProm_title(String prom_title) {
                this.prom_title = prom_title;
            }
        }
    }

    public static class CouponNumBean {
        /**
         * id : 165
         * uid : 50
         * cid : 29
         * use_start_time : 1512118877
         * use_end_time : 1519894877
         * name : 满20减20
         * money : 20.00
         * condition : 20.00
         */

        private int id;
        private int uid;
        private int cid;
        private int use_start_time;
        private int use_end_time;
        private String name;
        private String money;
        private String condition;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getUse_start_time() {
            return use_start_time;
        }

        public void setUse_start_time(int use_start_time) {
            this.use_start_time = use_start_time;
        }

        public int getUse_end_time() {
            return use_end_time;
        }

        public void setUse_end_time(int use_end_time) {
            this.use_end_time = use_end_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }
    }
}
