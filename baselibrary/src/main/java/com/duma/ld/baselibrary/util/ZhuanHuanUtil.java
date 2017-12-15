package com.duma.ld.baselibrary.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import com.duma.ld.baselibrary.base.BaseApplication;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by liudong on 2017/6/8.
 */

public class ZhuanHuanUtil {
    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(BaseApplication.getInstance(), id);
    }

    @ColorInt
    public static int getColor(@ColorRes int id) {
        return ContextCompat.getColor(BaseApplication.getInstance(), id);
    }

    public static String Time2nian(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(l);
    }

    public static String Time2ShiFen(long l) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(l);
    }

    public static String Time2nian2(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(l);
    }

    public static String Time2fen(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(l);
    }


    //10分钟时间戳
    public static long getMinute(long min) {
        return min * 60 * 1000;
    }


    public static String setNumOr(double Birthday) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(Birthday);
    }

    public static String setNumOr0(double Birthday) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(Birthday);
    }

    public static String setNumOr00(float Birthday) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(Birthday);
    }

    public static String setPhoneXX(String phone) {
        if (phone.length() != 11) {
            return phone;
        }
        String substring = phone.substring(3, 7);
        return phone.replace(substring, "****");
    }

    public static String setIdCardXX(String idCard) {
        int length = idCard.length();
        if (length != 18) {
            return idCard;
        }
        String substring = idCard.substring(1, 17);
        return idCard.replace(substring, "****************");
    }
}
