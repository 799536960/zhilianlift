package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.Intent;

import com.duma.ld.zhilianlift.model.AddresModel;
import com.duma.ld.zhilianlift.model.RealNameModel;
import com.duma.ld.zhilianlift.view.login.LoginOrRegisterActivity;
import com.duma.ld.zhilianlift.view.main.shopping.GoodsListActivity;
import com.duma.ld.zhilianlift.view.main.wode.VerifyPhoneActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddOrChangeActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddresListActivity;
import com.duma.ld.zhilianlift.view.main.wode.UserDataActivity;
import com.duma.ld.zhilianlift.view.main.wode.realName.RealNameEditActivity;

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
}
