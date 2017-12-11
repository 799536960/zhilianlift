package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.Intent;

import com.duma.ld.zhilianlift.view.login.LoginOrRegisterActivity;
import com.duma.ld.zhilianlift.view.main.shopping.GoodsListActivity;
import com.duma.ld.zhilianlift.view.main.wode.addres.AddresListActivity;
import com.duma.ld.zhilianlift.view.main.wode.userdata.UserDataActivity;

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
}
