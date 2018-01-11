package com.duma.ld.zhilianlift.util;

/**
 * Created by liudong on 2017/12/1.
 */

public class HttpUrl {
    public static String BaseUrl = "http://192.168.0.58:8081";
    //    public static String BaseUrl = "http://192.168.0.94:8080";
    /**
     * webview
     */
    public static String logistics = BaseUrl + "/index.php/mobile/Goods/logistics?order_id=";

    /**
     * api
     */
    public static String homePage = BaseUrl + "/index.php/api/index/homePage";
    public static String classMian = BaseUrl + "/index.php/api/goods/allCategoryList";
    public static String getcity = BaseUrl + "/index.php/api/index/getcity";
    public static String get_region = BaseUrl + "/index.php/api/index/get_region";
    public static String hot_keywords = BaseUrl + "/index.php/api/index/hot_keywords";
    public static String send_validate_code = BaseUrl + "/index.php/api/User/send_validate_code";
    public static String forgetpassword = BaseUrl + "/index.php/api/user/forgetpassword";
    public static String reg = BaseUrl + "/index.php/api/user/reg";
    public static String login = BaseUrl + "/index.php/api/user/login";
    public static String userInfo = BaseUrl + "/index.php/api/user/userInfo";
    public static String upload_headpic = BaseUrl + "/index.php/api/user/upload_headpic";
    public static String updateUserInfo = BaseUrl + "/index.php/api/user/updateUserInfo";
    public static String getprovince = BaseUrl + "/index.php/api/index/getprovince";
    public static String addAddress = BaseUrl + "/index.php/api/user/addAddress";
    public static String getAddressList = BaseUrl + "/index.php/api/user/getAddressList";
    public static String del_address = BaseUrl + "/index.php/api/user/del_address";
    public static String setDefaultAddress = BaseUrl + "/index.php/api/user/setDefaultAddress";
    public static String getcertification = BaseUrl + "/index.php/api/user/getcertification";
    public static String check_sms = BaseUrl + "/index.php/api/user/check_sms";
    public static String certification = BaseUrl + "/index.php/api/user/certification";
    public static String suggestion = BaseUrl + "/index.php/api/user/suggestion";
    public static String password = BaseUrl + "/index.php/api/user/password";
    public static String mobile = BaseUrl + "/index.php/api/user/mobile";
    public static String paypwd_is = BaseUrl + "/index.php/api/user/paypwd_is";
    public static String setpaypwd = BaseUrl + "/index.php/api/user/setpaypwd";
    public static String paypwd = BaseUrl + "/index.php/api/user/paypwd";
    public static String goodsList = BaseUrl + "/index.php/api/Goods/goodsList";
    public static String search = BaseUrl + "/index.php/api/goods/search";
    public static String goodsInfo = BaseUrl + "/index.php/api/Goods/goodsInfo";
    public static String goodsContent = BaseUrl + "/index.php/api/Goods/goodsContent";
    public static String getGoodsComment = BaseUrl + "/index.php/api/Goods/getGoodsComment";
    public static String getInfo = BaseUrl + "/index.php/api/Cart/getInfo";
    public static String addCart = BaseUrl + "/index.php/api/Cart/addCart";
    public static String collectGoodsOrNo = BaseUrl + "/index.php/api/goods/collectGoodsOrNo";
    public static String cartList = BaseUrl + "/index.php/api/Cart/cartList";
    public static String changeNum = BaseUrl + "/index.php/api/Cart/changeNum";
    public static String changeGoodsNum = BaseUrl + "/index.php/api/Cart/changeGoodsNum";
    public static String change = BaseUrl + "/index.php/api/Cart/change";
    public static String visit_log = BaseUrl + "/index.php/api/user/visit_log";
    public static String del_visit_log = BaseUrl + "/index.php/api/user/del_visit_log";
    public static String clear_visit_log = BaseUrl + "/index.php/api/user/clear_visit_log";
    public static String collectgoodall = BaseUrl + "/index.php/api/user/collectgoodall";
    public static String collectgoodlist = BaseUrl + "/index.php/api/user/collectgoodlist";
    public static String collectgoodNo = BaseUrl + "/index.php/api/user/collectgoodNo";
    public static String Cart2 = BaseUrl + "/index.php/api/Cart/Cart2";
    public static String getCouponList = BaseUrl + "/index.php/api/Cart/getCouponList";
    public static String Cart3 = BaseUrl + "/index.php/api/Cart/Cart3";
    public static String getOrderList = BaseUrl + "/index.php/api/user/getOrderList";
    public static String order_detail = BaseUrl + "/index.php/api/order/order_detail";
    public static String cancelOrder = BaseUrl + "/index.php/api/user/cancelOrder";
    public static String orderConfirm = BaseUrl + "/index.php/api/user/orderConfirm";
    public static String buy = BaseUrl + "/index.php/api/user/buy";
    public static String del_order = BaseUrl + "/index.php/api/order/del_order";
}
