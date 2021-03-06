package com.duma.ld.zhilianlift.util;

/**
 * 常量
 * Created by liudong on 2017/9/5.
 */

public class Constants {
    // 再点一次退出程序时间设置
    public static final long WAIT_TIME = 2000L;
    public static long TOUCH_TIME = 0;
    public static final String kefu = "0571-28227987";
    public static final String youxiang = "service@saibertron.com";
    public static final String Page = "p";
    public static final String Size = "size";
    public static final String Weixin = "wx43bcc455bb86ae68";
    //支付宝
    public static final int SDK_PAY_FLAG = 1;
    //默认城市
    public static final String defaultCity = "湖州市";
    public static final String defaultCity_code = "330500";
    public static final String locationString = "加载中..";

    //dialog 选择规格类型
    public static final String addShopCart = "addShopCart";
    public static final String shop = "shop";
    public static final String ok = "ok";
    /**
     * event
     */
    public static final int event_location_home = 1;
    public static final int event_location_city = 2;
    public static final int event_select_city = 3;
    public static final int event_codeMax_success = 4;
    //更新收货地址的viewpager
    public static final int event_addres_refresh = 5;
    //添加收货地址的地区选择
    public static final int event_addres_add = 6;
    //添加收货地址列表
    public static final int event_addresList_add = 7;
    //编辑收货地址列表
    public static final int event_addresList_edit = 8;
    //设置支付密码成功的回调
    public static final int event_pay_success = 9;
    //在确认订单也选择地址后回调
    public static final int event_address = 10;
    //在确认订单也选择优惠券
    public static final int event_conpons = 11;
    //输入支付密码页面 输入完成
    public static final int event_pay_password = 12;
    //设置支付密码成功的回调 在确认订单页面
    public static final int event_pay_success_order = 13;
    //订单详情页向订单列表页发送的页面更改事件
    public static final int event_refresh_order = 14;
    //评论像订单详情和列表发更新事件
    public static final int event_refresh_order_comment = 15;
    //支付宝支付成功
    public static final int event_zhifuBao_success = 16;
    //银联支付成功
    public static final int event_yinlian_success = 19;
    //微信支付成功
    public static final int event_weixin_success = 18;
    //添加支付宝账号成功 刷新
    public static final int event_addZhiFuBao_success = 17;
    /**
     * intent
     */
    public static final String id = "id";
    public static final String position = "position";
    public static final String key = "key";
    public static final String Name = "name";
    public static final String Type = "Type";
    public static final String Res = "Res";
    public static final String ClassId = "ClassId";
    public static final String SearchString = "SearchString";
    public static final String Url = "Url";
    public static final String Model = "model";
    public static final String ViewInfo = "ViewInfo";

    public static final String PROPNAME_SCREENLOCATION_LEFT = "PROPNAME_SCREENLOCATION_LEFT";
    public static final String PROPNAME_SCREENLOCATION_TOP = "PROPNAME_SCREENLOCATION_TOP";
    public static final String PROPNAME_WIDTH = "PROPNAME_WIDTH";
    public static final String PROPNAME_HEIGHT = "PROPNAME_HEIGHT";

    //验证手机后 跳转页面类型
    //跳转实名认证
    public static final String Verify_RealName = "Verify_RealName";
    public static final String Verify_payPassword = "Verify_payPassword";
    public static final String Verify_payPassword_forget = "Verify_payPassword_forget";

    //支付密码
    //第一次设置支付密码
    public static final String type_new = "type_new";
    //验证原支付密码
    public static final String type_verify = "type_verify";
    //验证后设置支付密码
    public static final String type_verify_new = "type_verify_new";
    //忘记支付密码 验证手机后设置新支付密码
    public static final String type_forget_verify_new = "type_forget_verify_new";
    /**
     * sp
     */
    public static final String sp_Location = "sp_Location"; //用户位置信息
    public static final String sp_User = "sp_User"; //用户信息
    public static final String sp_Api = "sp_Api"; //是否启动api页面


    /**
     * 订单
     * 待付款->取消订单 去支付
     * 待发货->  --     查看详情
     * 待收货->查看物流 确认收货
     * 待评价->再次购买 评价
     * 已取消->删除订单 再次购买
     * 已完成->删除订单 再次购买
     */
    public static final String Order_Type_DaiFuKuan = "WAITPAY";
    public static final String Order_Type_DaiFaHuo = "WAITSEND";
    public static final String Order_Type_DaiShouHuo = "WAITRECEIVE";
    public static final String Order_Type_DaiPinJia = "WAITCCOMMENT";
    public static final String Order_Type_YiQuXiao = "CANCEL";
    public static final String Order_Type_YiWanChen = "FINISH";

    public static final String Order_Text_QuXiaoDinDan = "取消订单";
    public static final String Order_Text_QuZhiFu = "去支付";
    public static final String Order_Text_ChaKanXiangQin = "查看详情";
    public static final String Order_Text_ChaKanWuLiu = "查看物流";
    public static final String Order_Text_QueRenShouHuo = "确认收货";
    public static final String Order_Text_ZaiCiGouMai = "再次购买";
    public static final String Order_Text_PinJia = "评价";
    public static final String Order_Text_ShanChuDinDan = "删除订单";
}
