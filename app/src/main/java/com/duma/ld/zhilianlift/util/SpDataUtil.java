package com.duma.ld.zhilianlift.util;

import com.baidu.location.BDLocation;
import com.blankj.utilcode.util.SPUtils;

import static com.duma.ld.zhilianlift.util.Constants.defaultCity;

/**
 * Created by liudong on 2017/12/4.
 */

public class SpDataUtil {
    public static void setLocation(BDLocation bdLocation) {
        SPUtils.getInstance().put(Constants.sp_latitude, bdLocation.getLatitude() + "");
        SPUtils.getInstance().put(Constants.sp_longitude, bdLocation.getLongitude() + "");
        SPUtils.getInstance().put(Constants.sp_addr, bdLocation.getAddress() + "");
        SPUtils.getInstance().put(Constants.sp_province, bdLocation.getProvince() + "");
        SPUtils.getInstance().put(Constants.sp_city, bdLocation.getCity() + "");
        SPUtils.getInstance().put(Constants.sp_district, bdLocation.getDistrict() + "");
        SPUtils.getInstance().put(Constants.sp_street, bdLocation.getStreet() + "");
    }

    public static void setCity(String city) {
        SPUtils.getInstance().put(Constants.sp_city, city + "");
        SPUtils.getInstance().put(Constants.sp_latitude, "");
        SPUtils.getInstance().put(Constants.sp_longitude, "");
        SPUtils.getInstance().put(Constants.sp_addr, "");
        SPUtils.getInstance().put(Constants.sp_province, "");
        SPUtils.getInstance().put(Constants.sp_district, "");
        SPUtils.getInstance().put(Constants.sp_street, "");
    }

    public static void setDistrict(String district) {
        SPUtils.getInstance().put(Constants.sp_district, district);
        SPUtils.getInstance().put(Constants.sp_latitude, "");
        SPUtils.getInstance().put(Constants.sp_longitude, "");
        SPUtils.getInstance().put(Constants.sp_addr, "");
        SPUtils.getInstance().put(Constants.sp_province, "");
        SPUtils.getInstance().put(Constants.sp_street, "");
    }

    public static boolean isCity(String city) {
        return getCity().equals(city);
    }

    //返回当前城市
    public static String getCity() {
        return SPUtils.getInstance().getString(Constants.sp_city, defaultCity);
    }

    public static boolean isLogin() {
        return false;
    }


}
