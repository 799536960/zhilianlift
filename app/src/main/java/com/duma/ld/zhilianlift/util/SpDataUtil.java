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

    public static boolean isCity(BDLocation bdLocation) {
        return getCity().equals(bdLocation.getCity());
    }

    //返回当前城市
    public static String getCity() {
        return SPUtils.getInstance().getString(Constants.sp_city, defaultCity);
    }
}
