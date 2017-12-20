package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.Intent;

import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.RealNameModel;
import com.duma.ld.zhilianlift.view.login.LoginOrRegisterActivity;
import com.duma.ld.zhilianlift.view.main.home.HomeActivity;
import com.duma.ld.zhilianlift.view.main.shopping.GoodsListActivity;
import com.duma.ld.zhilianlift.view.main.shopping.SearchActivity;
import com.duma.ld.zhilianlift.view.main.wode.UserDataActivity;
import com.duma.ld.zhilianlift.view.main.wode.VerifyPhoneActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddOrChangeActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddresListActivity;
import com.duma.ld.zhilianlift.view.main.wode.realName.RealNameEditActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.PayPasswordActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.PaySettingActivity;
import com.duma.ld.zhilianlift.view.main.wode.userSecuryty.PaySuccessActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.duma.ld.zhilianlift.util.Constants.ClassId;
import static com.duma.ld.zhilianlift.util.Constants.Res;
import static com.duma.ld.zhilianlift.util.Constants.SearchString;
import static com.duma.ld.zhilianlift.util.Constants.Type;

/**
 * Created by liudong on 2017/12/6.
 */

public class IntentUtil {
    public static void goGoodsList_class(Activity activity, String classId) {
        goGoodsList(activity, ClassId, classId);
    }

    public static void goGoodsList_search(Activity activity, String searchString) {
        goGoodsList(activity, SearchString, searchString);
    }

    /**
     * 跳转商品列表也
     *
     * @param activity content
     * @param type     类型 分类和搜索
     * @param res      携带的值 分类的话就是分类id 搜索就是搜索内容
     */
    private static void goGoodsList(Activity activity, String type, String res) {
        Intent intent = new Intent(activity, GoodsListActivity.class);
        intent.putExtra(Type, type);
        intent.putExtra(Res, res);
        activity.startActivity(intent);
    }

    public static void goLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginOrRegisterActivity.class);
        activity.startActivity(intent);
    }

    public static void goUserData(Activity activity) {
        Intent intent = new Intent(activity, UserDataActivity.class);
        activity.startActivity(intent);
    }

    public static void goAddresList(Activity activity) {
        Intent intent = new Intent(activity, AddresListActivity.class);
        activity.startActivity(intent);
    }

    public static void goAdd_Addres(Activity activity) {
        goAddOrChange(activity, null);
    }

    public static void goAddOrChange(Activity activity, AddresModel model) {
        Intent intent = new Intent(activity, AddOrChangeActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }

    /**
     * 跳转验证手机
     *
     * @param type 类型
     */
    private static void goVerifyPhone(Activity activity, String type) {
        Intent intent = new Intent(activity, VerifyPhoneActivity.class);
        intent.putExtra(Type, type);
        activity.startActivity(intent);
    }

    //验证手机后 跳转实名认证
    public static void goVerifyPhone_RealName(Activity activity) {
        goVerifyPhone(activity, Constants.Verify_RealName);
    }

    //验证手机后 跳转设置支付密码
    public static void goVerifyPhone_payPassword(Activity activity) {
        goVerifyPhone(activity, Constants.Verify_payPassword);
    }

    //验证手机后 跳转忘记密码的设置支付密码
    public static void goVerifyPhone_payPassword_forget(Activity activity) {
        goVerifyPhone(activity, Constants.Verify_payPassword_forget);
    }

    //添加实名认证
    public static void goRealName_add(Activity activity) {
        goRealName(activity, null);
    }

    //修改实名认证
    public static void goRealName_change(Activity activity, RealNameModel model) {
        goRealName(activity, model);
    }

    private static void goRealName(Activity activity, RealNameModel model) {
        Intent intent = new Intent(activity, RealNameEditActivity.class);
        intent.putExtra(Constants.Model, model);
        activity.startActivity(intent);
    }

    public static void goMain(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    //支付设置页面
    public static void goPaySetting(Activity activity) {
        Intent intent = new Intent(activity, PaySettingActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    //支付密码页面
    public static void goPayPassword(Activity activity, String type) {
        /**
         * //第一次设置支付密码 "type_new";
         * //验证原支付密码 "type_verify";
         * //验证后设置支付密码 "type_verify_new";
         * //忘记支付密码 验证手机后设置新支付密码 "type_forget_verify_new";
         */
        Intent intent = new Intent(activity, PayPasswordActivity.class);
        intent.putExtra(Constants.Type, type);
        activity.startActivity(intent);
    }

    //支付密码成功页面
    public static void goPaySuccess(Activity activity, String type) {
        Intent intent = new Intent(activity, PaySuccessActivity.class);
        intent.putExtra(Constants.Type, type);
        activity.startActivity(intent);
    }

    //搜索页面
    public static void goSearch(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }
}
