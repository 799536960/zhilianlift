package com.duma.ld.zhilianlift.util;

/**
 * Created by liudong on 2017/12/1.
 */

public class HttpUrl {
    public static String BaseUrl = "http://192.168.0.58:8081";
    //    public static String BaseUrl = "http://192.168.0.94:8080";


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
}
