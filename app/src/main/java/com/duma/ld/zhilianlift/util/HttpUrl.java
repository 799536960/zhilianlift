package com.duma.ld.zhilianlift.util;

/**
 * 接口
 * Created by liudong on 2017/12/1.
 */

public class HttpUrl {
    //        public static String BaseUrl = "http://192.168.0.58:8081";
    public static String BaseUrl = "http://www.duma-ivy.cn:8080";
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
    public static String comment_list = BaseUrl + "/index.php/api/user/comment_list";
    public static String upload_comment_img = BaseUrl + "/index.php/api/user/upload_comment_img";
    public static String addComment = BaseUrl + "/index.php/api/user/addComment";
    public static String get_return_goods_status = BaseUrl + "/index.php/api/order/get_return_goods_status";
    public static String return_goods = BaseUrl + "/index.php/api/order/return_goods";
    public static String return_list = BaseUrl + "/index.php/api/order/return_list";
    public static String return_goods_info = BaseUrl + "/index.php/api/order/return_goods_info";
    public static String getNews = BaseUrl + "/index.php/api/user/getNews";
    public static String del_new = BaseUrl + "/index.php/api/user/del_new";
    public static String clear_new = BaseUrl + "/index.php/api/user/clear_new";
    public static String account_log = BaseUrl + "/index.php/api/user/account_log";
    public static String getALL = BaseUrl + "/index.php/api/house/getALL";
    public static String second = BaseUrl + "/index.php/api/house/second";
    public static String lease = BaseUrl + "/index.php/api/house/lease";
    public static String getMyHouse = BaseUrl + "/index.php/api/house/getMyHouse";
    public static String deleteHouse = BaseUrl + "/index.php/api/house/deleteHouse";
    public static String editHouseStatus = BaseUrl + "/index.php/api/house/editHouseStatus";
    public static String getList3 = BaseUrl + "/index.php/api/house/getList3";
    public static String getList2 = BaseUrl + "/index.php/api/house/getList2";
    public static String getList1 = BaseUrl + "/index.php/api/house/getList1";
    public static String getALL1 = BaseUrl + "/index.php/api/house/getALL1";
    public static String getALL3 = BaseUrl + "/index.php/api/house/getALL3";
    public static String getALL2 = BaseUrl + "/index.php/api/house/getALL2";
    public static String gethoustInfo = BaseUrl + "/index.php/api/house/gethoustInfo";
    public static String collectHouseOrNo = BaseUrl + "/index.php/api/house/collectHouseOrNo";
    public static String getdoorinfo = BaseUrl + "/index.php/api/house/getdoorinfo";
    public static String addPreparation = BaseUrl + "/index.php/api/house/addPreparation";
    public static String myPreparation = BaseUrl + "/index.php/api/house/myPreparation";
    public static String editPreparationStatus = BaseUrl + "/index.php/api/house/editPreparationStatus";
    public static String ThereCategoryList = BaseUrl + "/index.php/api/goods/ThereCategoryList";
    public static String TwoCategoryList = BaseUrl + "/index.php/api/goods/TwoCategoryList";
    public static String StoreByBrand = BaseUrl + "/index.php/api/Store/StoreByBrand";
    public static String plan = BaseUrl + "/index.php/api/user/plan";
    public static String getCredit = BaseUrl + "/index.php/api/user/getCredit";
    public static String credit = BaseUrl + "/index.php/api/user/credit";
    public static String getCreditList = BaseUrl + "/index.php/api/user/getCreditList";
    public static String getCredit_Schedule = BaseUrl + "/index.php/api/user/getCredit_Schedule";
    public static String getStoreInfo = BaseUrl + "/index.php/api/user/getStoreInfo";
    public static String getRenovationCat = BaseUrl + "/index.php/api/user/getRenovationCat";
    public static String StoreMoney = BaseUrl + "/index.php/api/user/StoreMoney";
    public static String get_code = BaseUrl + "/index.php/api/Alipay/get_code";
    public static String addRecharge = BaseUrl + "/index.php/api/user/addRecharge";
    public static String getalipay = BaseUrl + "/index.php/api/user/getalipay";
    public static String editalipay = BaseUrl + "/index.php/api/user/editalipay";
    public static String addtixian = BaseUrl + "/index.php/api/user/addtixian";
    public static String get_code2 = BaseUrl + "/index.php/api/wxpay/get_code";
    public static String get_code_recharge = BaseUrl + "/index.php/api/wxpay/get_code_recharge";
    public static String UnionPay = BaseUrl + "/index.php/api/Union/UnionPay";
    public static String UnRecharge= BaseUrl + "/index.php/api/Union/UnRecharge";
}
