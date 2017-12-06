package com.duma.ld.zhilianlift.util;

import android.app.Activity;
import android.content.Intent;

import com.duma.ld.zhilianlift.view.main.shopping.GoodsListActivity;

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

    public static void goGoodsList(Activity activity, String type, String res) {
        Intent intent = new Intent(activity, GoodsListActivity.class);
        intent.putExtra(Type, type);
        intent.putExtra(Res, res);
        activity.startActivity(intent);
    }
}
