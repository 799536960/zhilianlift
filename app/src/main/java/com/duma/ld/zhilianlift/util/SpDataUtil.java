package com.duma.ld.zhilianlift.util;

import com.baidu.location.BDLocation;
import com.blankj.utilcode.util.SPUtils;
import com.duma.ld.zhilianlift.model.UserModel;
import com.google.gson.Gson;

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
        if (getUser() == null) {
            return false;
        }
        return true;
    }

    public static void setUser(UserModel userModel) {
        SPUtils.getInstance().put(Constants.sp_User, new Gson().toJson(userModel));
    }

    public static void removeUser() {
        SPUtils.getInstance().put(Constants.sp_User, "");
    }

    public static UserModel getUser() {
        String string = SPUtils.getInstance().getString(Constants.sp_User, "");
        if (string.isEmpty()) {
            return null;
        }
        UserModel userModel = new Gson().fromJson(string, UserModel.class);
        if (userModel.getToken() == null || userModel.getToken().isEmpty()) {
            return null;
        }
        return userModel;
    }

    //保存头像
    public static void setImgHead(String url) {
        UserModel user = getUser();
        if (user != null) {
            user.setHead_pic(url);
        }
        setUser(user);
    }

    //保存生日
    public static void setBirthday(long time) {
        UserModel user = getUser();
        if (user != null) {
            user.setBirthday(time);
        }
        setUser(user);
    }

    //保存性别
    public static void setSex(int sex) {
        UserModel user = getUser();
        if (user != null) {
            user.setSex(sex);
        }
        setUser(user);
    }

    //保存昵称
    public static void setNiceName(String niceName) {
        UserModel user = getUser();
        if (user != null) {
            user.setNickname(niceName);
        }
        setUser(user);
    }

    public static String getToken() {
        UserModel user = getUser();
        if (user == null || user.getToken() == null || user.getToken().isEmpty()) {
            removeUser();
            return "";
        } else {
            return user.getToken();
        }
    }

    public static long getUserId() {
        UserModel user = getUser();
        if (user == null) {
            removeUser();
            return 0;
        } else {
            return user.getUser_id();
        }
    }
}
