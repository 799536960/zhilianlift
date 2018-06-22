package com.duma.ld.zhilianlift.util;

import com.baidu.location.BDLocation;
import com.blankj.utilcode.util.SPUtils;
import com.duma.ld.baselibrary.util.EventBusUtil;
import com.duma.ld.zhilianlift.model.BaiDuLocationModel;
import com.duma.ld.zhilianlift.model.LocationModel;
import com.duma.ld.zhilianlift.model.UserModel;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

import static com.duma.ld.zhilianlift.util.Constants.sp_Hide;
import static com.duma.ld.zhilianlift.util.Constants.sp_Location;

/**
 * Created by liudong on 2017/12/4.
 */

public class SpDataUtil {
    //用定位信息获取有区划代码信息
    public static void setLocation(final int type, BDLocation bdLocation) {
        OkGo.getInstance().cancelTag("setLocation");
        OkGo.<String>get("http://api.map.baidu.com/geocoder/v2/")
                .tag("setLocation")
                .params("pois", "0")
                .params("latest_admin", "1")
                .params("output", "json")
                .params("ak", "qxNH2SKh5Vy2xNnGGgIIeqDg39uQZaCe")
                .params("location", bdLocation.getLatitude() + "," + bdLocation.getLongitude())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaiDuLocationModel model = new Gson().fromJson(response.body(), BaiDuLocationModel.class);
                        if (model.getStatus() == 0) {
                            EventBusUtil.sendModel(type, model);
                        }
                    }
                });
    }

    //缓存
    public static void setLocationData(BaiDuLocationModel model) {
        BaiDuLocationModel.ResultBean.AddressComponentBean addressComponent = model.getResult().getAddressComponent();
        LocationModel locationModel = new LocationModel(addressComponent.getCity(), addressComponent.getDistrict(), addressComponent.getAdcode());
        locationModel.setmLatitude(model.getResult().getLocation().getLat());
        locationModel.setmLongitude(model.getResult().getLocation().getLng());
        setData(locationModel);
    }

    public static void setData(LocationModel locationModel) {
        Logger.e(locationModel.toString());
        SPUtils.getInstance().put(Constants.sp_Location, new Gson().toJson(locationModel));
    }

    public static void setCity(String city, String code) {
        LocationModel location = new LocationModel(city, "", code);
        setData(location);
    }

    public static void setDistrict(String district, String code) {
        LocationModel location = getLocation();
        location.setDistrict(district);
        location.setCode(code);
        setData(location);
    }

    public static boolean isCity(String city) {
        return getCity().equals(city);
    }

    /**
     * 返回位置信息
     * 没有返回默认的
     *
     * @return 位置信息
     */
    public static LocationModel getLocation() {
        String string = SPUtils.getInstance().getString(sp_Location, "");
        return LocationModel.getJsonModel(string);
    }

    public static boolean isHide() {
        return SPUtils.getInstance().getBoolean(sp_Hide, true);
    }

    public static void setHide(String hide) {
        if (hide.equals("Yes")) {
            SPUtils.getInstance().put(sp_Hide, false);
        } else {
            SPUtils.getInstance().put(sp_Hide, true);
        }
    }

    //返回当前城市
    public static String getCity() {
        return getLocation().getCity();
    }

    /**
     * @return true 登录了
     */
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

    /**
     * 获取消息数量
     */
    public static String getMessageNum() {
        UserModel user = SpDataUtil.getUser();
        if (user == null) {
            return "";
        }
        return user.getNews();
    }

    /**
     * 设置消息数量
     */
    public static void setMessageNum(String num) {
        UserModel user = SpDataUtil.getUser();
        if (user == null) {
            return;
        }
        user.setNews(num);
    }
}
