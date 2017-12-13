package com.duma.ld.zhilianlift.util;

/**
 * Created by liudong on 2017/9/5.
 */

public class Constants {
    //默认城市
    public static final String defaultCity = "湖州市";
    public static final String locationString = "加载中..";
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

    /**
     * intent
     */
    public static final String key = "key";
    public static final String Name = "name";
    public static final String Type = "Type";
    public static final String Res = "Res";
    public static final String ClassId = "ClassId";
    public static final String SearchString = "SearchString";
    public static final String Url = "Url";
    /**
     * sp
     */
    public static final String sp_addr = "sp_addr"; //获取详细地址信息
    public static final String sp_province = "sp_province"; //获取省份
    public static final String sp_city = "sp_city";  //获取城市
    public static final String sp_district = "sp_district";//获取区县
    public static final String sp_street = "sp_street";//获取街道信息
    public static final String sp_latitude = "sp_latitude"; //获取纬度信息
    public static final String sp_longitude = "sp_longitude"; //获取经度信息
    public static final String sp_User = "sp_User"; //用户信息

    /**
     * 首页 type
     */
    public static final int type_1 = 1; //商品 goodsModel不能为空
    public static final int type_2 = 2; //分类 id title imgUrl
    public static final int type_3 = 3; //网页 h5 url title  imgUrl不能为空
    public static final int type_4 = 4; //搜索 title  imgUrl不能为空
}
